package com.presto.common.exception;

/**
 * Created by shihao on 16/10/29.
 */
public enum CommonExceptionCode {


    E999999("系统异常！")
    ;

    /**
     * 消息
     */
    private String message;

    private CommonExceptionCode(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }
}
