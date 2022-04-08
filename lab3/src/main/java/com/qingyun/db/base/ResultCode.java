package com.qingyun.db.base;

/**
 * @description： 返回数据的状态码
 * @author: 張青云
 * @create: 2022-04-08 22:17
 **/
public interface ResultCode {
    /**
     * 成功
     */
    int SUCCESS = 20000;

    /**
     * 出现异常
     */
    int ERROR = 50000;
}
