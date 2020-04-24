package com.community.cyd.dto;

import lombok.Data;

/**
 * 热点新闻
 * */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO)o).getPriority();
    }
}
