package com.community.cyd.dto;

import lombok.Data;

@Data
public class QuestionQueryDTO {
    private Integer page;
    private Integer size;
    private Integer totalCount;
    private String search;
    private String tag;
}
