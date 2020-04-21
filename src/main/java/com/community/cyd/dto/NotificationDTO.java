package com.community.cyd.dto;

import lombok.Data;

/**
 * 通知DTO
 * */

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;  //评论者id
    private String notifierName;  //评论者Name
    private String outerTitle;  //评论的标题
    private Long outerId;  //用于跳转的Id
    private String typeName;  //回复的类型名字
    private Integer type; //回复的类型
}
