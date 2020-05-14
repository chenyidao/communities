package com.community.cyd.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;// 用户名
    private Integer id;    // 用户id
    private String bio; // 描述
    private String avatarUrl; //头像地址
}
