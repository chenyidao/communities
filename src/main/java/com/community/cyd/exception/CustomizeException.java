package com.community.cyd.exception;

/**
 * 抛出的异常类
 *
 * **/
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
