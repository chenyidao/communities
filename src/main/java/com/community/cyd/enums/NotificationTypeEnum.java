package com.community.cyd.enums;

/**
 * 回复类型枚举类，1表示回复问题，2表示回复评论
 * **/
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(1,"回复了问题");

    private int type;
    private String name;

    public int getType(){
        return type;
    }
    public String getName(){
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
