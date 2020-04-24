package com.community.cyd.schedule;

import com.community.cyd.cache.HotTagCache;
import com.community.cyd.mapper.QuestionMapper;
import com.community.cyd.model.Question;
import com.community.cyd.model.QuestionExample;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime() {
        int offset = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}", new Date());

        List<Question> list = new ArrayList<>();
        Map<String, Integer> priorities = new HashMap<>();

        //计算优先级
        //获取全部问题列表，通过公式 priority = priority + 5 + commentCount 每隔一段时间计算优先级
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorities.put(tag, 5 + question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }
        //获取top3
        hotTagCache.updateTags(priorities);
        log.info("hotTagSchedule stop {}", new Date());
    }
}
