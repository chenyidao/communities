package com.community.cyd.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    String categoryName;
    List<String> tags;
}
