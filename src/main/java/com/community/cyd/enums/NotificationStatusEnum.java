package com.community.cyd.enums;

/**
 * 状态枚举类，0表示未读，1表示已读
 **/
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
