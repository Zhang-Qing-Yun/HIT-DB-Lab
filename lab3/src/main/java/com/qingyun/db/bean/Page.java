package com.qingyun.db.bean;

import lombok.Data;

/**
 * @description： 封装分页信息
 * @author: 張青云
 * @create: 2022-04-08 23:08
 **/
@Data
public class Page {
    //  当前页码
    private int current = 1;

    //  一页显示多少条数据
    private int limit = 50;

    //  数据总数（用于计算总页数）
    private int rows;

    // 查询路径（用于复用分页链接）
    private String path;


    /**
     * 获取当前页的起始行
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     */
    public int getTotal() {
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }

    /**
     * 获取分页条的起始页码
     */
    public int getFrom() {
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    /**
     * 获取分页条的结束页码
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
