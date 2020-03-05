package com.community.cyd.dto;

import com.community.cyd.model.User;
import lombok.Data;

/**
 * 数据库中读取到的comment数据组成的传到前端的comment dto
 * **/
@Data
public class CommentRespDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private String content;
    private Long commentator;
    private Long likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private User user;
}
