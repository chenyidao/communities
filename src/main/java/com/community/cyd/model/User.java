package com.community.cyd.model;

import lombok.Data;

//对应数据库中的User表
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
