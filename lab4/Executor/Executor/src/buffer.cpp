/**
 * @author See Contributors.txt for code contributors and overview of BadgerDB.
 *
 * @section LICENSE
 * Copyright (c) 2012 Database Group, Computer Sciences Department, University of Wisconsin-Madison.
 */

#include <memory>
#include <iostream>
#include "buffer.h"
#include "exceptions/buffer_exceeded_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "exceptions/bad_buffer_exception.h"
#include "exceptions/hash_not_found_exception.h"

namespace badgerdb { 

BufMgr::BufMgr(std::uint32_t bufs)
	: numBufs(bufs) {
	bufDescTable = new BufDesc[bufs];  // 每个页面对应的描述对象

  for (FrameId i = 0; i < bufs; i++) 
  {
  	bufDescTable[i].frameNo = i;
  	bufDescTable[i].valid = false;
  }

  bufPool = new Page[bufs];  // 页面

	int htsize = ((((int) (bufs * 1.2))*2)/2)+1;
  hashTable = new BufHashTbl (htsize);  // allocate the buffer hash table

  clockHand = bufs - 1;
}


BufMgr::~BufMgr() {
	//  1.写回脏页
	for (FrameId i = 0; i < numBufs; i++)
	{
		if (bufDescTable[i].dirty)
		{
			bufDescTable->file->writePage(bufPool[i]);
		}
	}
	//  2.释放缓冲池内存
	delete[] bufPool;
	//  3.释放bufDescTable
	delete[] bufDescTable;
	//  4.释放hashTable
	delete hashTable;
}

void BufMgr::advanceClock() {
	//  将Clock算法中的指针移动到下一个页框
	clockHand = (clockHand + 1) % numBufs;
}

void BufMgr::allocBuf(FrameId & frame)  {
	//  判断是否有空闲页
	int count = 0;
	for (int i = 0; i < numBufs; i++)
	{
		if (bufDescTable[i].pinCnt > 0)
		{
			count++;
		}
	}
	if (count == numBufs)
	{
		throw BufferExceededException();
	}
	//  使用Clock算法挑选出一个空闲页框
	while (true) {
		if (bufDescTable[clockHand].refbit || bufDescTable[clockHand].pinCnt > 0) {
			bufDescTable[clockHand].refbit = false;
			advanceClock();
		} else {
			break;
		}
	}
	//  如果该页面为脏页则需要先去刷新脏页
	if(bufDescTable[clockHand].dirty) {
		bufDescTable[clockHand].file->writePage(bufPool[clockHand]);
		bufDescTable[clockHand].dirty = false;
	}
	//  如果当前页面有效，则从hashtable中删除掉
	if(bufDescTable[clockHand].valid) {
		try
		{
			hashTable->remove(bufDescTable[clockHand].file, bufDescTable[clockHand].pageNo);
		}
		catch(const std::exception& e)
		{
			//  do nothing
		}
	}
	bufDescTable[clockHand].Clear();
	frame = clockHand;
}

void BufMgr::readPage(File* file, const PageId pageNo, Page*& page) {
	FrameId frameId;  // 是缓存池中的哪个页面
	try  // 要访问的页面在缓存池中
	{
		hashTable->lookup(file, pageNo, frameId);
		bufDescTable[frameId].pinCnt++;
		bufDescTable[frameId].refbit = true;
		page = bufPool + frameId;
	}
	catch(HashNotFoundException e)  // 不在缓存中
	{
		//  在缓存池中申请一个空闲页面
		allocBuf(frameId);
		//  从磁盘中读入一个页面
		bufPool[frameId] = file->readPage(pageNo);
		//  在哈希表中建立映射
		hashTable->insert(file, pageNo, frameId);
		//  设置页面状态
		bufDescTable[frameId].Set(file, pageNo);
		page = bufPool + frameId;
	}
}

void BufMgr::unPinPage(File* file, const PageId pageNo, const bool dirty)  {
	FrameId frameId;  // 是缓存池中的哪个页面
	try  // 要访问的页面在缓存池中
	{
		hashTable->lookup(file, pageNo, frameId);
		//  pinCnt为0则抛异常
		if (bufDescTable[frameId].pinCnt == 0)
		{
			throw PageNotPinnedException(bufDescTable[frameId].file->filename(), bufDescTable[frameId].pageNo, frameId);
		}
		//  将pinCnt减一
		bufDescTable[frameId].pinCnt--;
		if (dirty)
		{
			bufDescTable[frameId].dirty = true;
		}
	}
	catch(HashNotFoundException e)  // 不在缓存中
	{
		//  不在缓存池中则直接返回
		return;
	}
}

void BufMgr::flushFile(const File* file)  {
	for (FrameId i = 0; i < numBufs; i++)
	{
		if (bufDescTable[i].file == file)
		{
			if (bufDescTable[i].pinCnt > 0)
			{
				throw PagePinnedException(bufDescTable[i].file->filename(), bufDescTable[i].pageNo, i);

			}
			if (bufDescTable[i].valid == false)
			{
				throw BadBufferException(i, bufDescTable[i].dirty, bufDescTable[i].valid, bufDescTable[i].refbit);
			}
			
			//  如果是脏页则需要刷盘
			if (bufDescTable[i].dirty)
			{
				bufDescTable[i].file->writePage(bufPool[i]);
				bufDescTable[i].dirty = false;
			}
			//  从哈希表中删除
			hashTable->remove(bufDescTable[i].file, bufDescTable[i].pageNo);
			//  重置页框状态
			bufDescTable[i].Clear();
		}
		
	}
	
}

void BufMgr::allocPage(File* file, PageId &pageNo, Page*& page) {
	FrameId frameId;
	Page p = file->allocatePage();
	allocBuf(frameId);
	bufPool[frameId] = p;
	pageNo = p.page_number();
	bufDescTable[frameId].Set(file, pageNo);
	page = bufPool + frameId;
	hashTable->insert(file, pageNo, frameId);
}

void BufMgr::disposePage(File* file, const PageId PageNo) {
	FrameId frameId;
	try
	{
    	//  检查是否在缓存池中
		hashTable->lookup(file, PageNo, frameId);
		hashTable->remove(file, PageNo);
		bufDescTable[frameId].Clear();
	}
	catch(HashNotFoundException e)
	{
		//  do nothing
	}
	file->deletePage(PageNo);
}

void BufMgr::printSelf(void)  {
  BufDesc* tmpbuf;
	int validFrames = 0;
  
  for (std::uint32_t i = 0; i < numBufs; i++)
	{
  	tmpbuf = &(bufDescTable[i]);
		std::cout << "FrameNo:" << i << " ";
		tmpbuf->Print();

  	if (tmpbuf->valid == true)
    	validFrames++;
  }

	std::cout << "Total Number of Valid Frames:" << validFrames << "\n";
}

}
