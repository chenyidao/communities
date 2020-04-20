package com.community.cyd.exception;

/**
 * 实现生产异常信息接口（状态码和信息）
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NOT_LOGIN(2003, "当前操作需要登陆，请登陆后重试！"),
    SUCCESS(200, "请求成功"),
    SYS_ERROR(2004, "服务器冒烟了，要不然您稍后试试？！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    CONTENT_IS_EMPTY(2007,"评论内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"不能获取别人的信息"),
    NOTIFICATION_NOT_FOUND(2009,"消息不存在");

    private Integer code;
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
