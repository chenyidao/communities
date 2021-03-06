package com.community.cyd.dto;

import lombok.Data;

/**
 * 前端传过来的comment dto
 * **/
@Data
public class CommentReqDTO {
    private Long parentId;  //父类id，可以是问题id，也可以是一级回复id
    private Integer type;   //1，2
    private String content; //回复内容
}
