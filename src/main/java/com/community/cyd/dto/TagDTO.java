package com.community.cyd.dto;

import lombok.Data;

import java.util.List;

/**
 * 标签DTO
 * */

@Data
public class TagDTO {
    String categoryName;
    List<String> tags;
}
