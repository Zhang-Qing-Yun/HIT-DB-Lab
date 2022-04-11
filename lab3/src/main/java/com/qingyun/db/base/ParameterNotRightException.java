package com.qingyun.db.base;

/**
 * @description： 参数不合法异常
 * @author: 張青云
 * @create: 2022-04-11 21:30
 **/
public class ParameterNotRightException extends Exception {
    public ParameterNotRightException() {
    }

    public ParameterNotRightException(String message) {
        super(message);
    }
}
