package com.community.cyd.mapper;

import com.community.cyd.model.Question;

public interface QuestionExtendMapper {
    int incViewCount(Question question);
}