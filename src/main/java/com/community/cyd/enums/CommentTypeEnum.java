package com.community.cyd.enums;

/**
 * 评论类型枚举类，1表示问题回复，2表示评论回复
 * **/
public enum CommentTypeEnum {
    QUESTION(1),     //相当于每次都调用构造函数创建一个新的对象
    COMMENT(2);

    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (type == value.type) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

}
