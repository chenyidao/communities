package com.community.cyd.cache;

import com.community.cyd.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 热门话题
 */

@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    //排序热门话题  选出top3
    public void updateTags(Map<String, Integer> tags) {
        int max = 10;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        //使用优先队列选出map中优先级最高的3个热门标签
        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagDTO);
            } else {
                HotTagDTO minHot = priorityQueue.peek();
                if (hotTagDTO.compareTo(minHot) > 0) {
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }
            }
        });
        //将热门的3个标签名字按次序放入list中
        List<String> sortedTags = new ArrayList<>();
        for (HotTagDTO poll : priorityQueue) {
            sortedTags.add(0, poll.getName());
        }
        hots = sortedTags;  //避免叠加
    }
}
