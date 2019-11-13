package com.community.cyd.service;

import com.community.cyd.mapper.QuestionMapper;
import com.community.cyd.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    public void create(Question question) {
        questionMapper.create(question);
    }
}
