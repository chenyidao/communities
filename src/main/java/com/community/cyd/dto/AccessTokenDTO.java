package com.community.cyd.dto;

import lombok.Data;

@Data
public class AccessTokenDTO {
    private String client_id;   //用户id
    private String client_secret;   //用户密码
    private String code;
    private String redirect_uri;    //重定向地址
    private String state;
}
