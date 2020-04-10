package com.community.cyd.mapper;

import com.community.cyd.model.Question;

import java.util.List;

public interface QuestionExtendMapper {
    int incViewCount(Question question);
    int incCommentCount(Question question);
    List<Question> selectRelatedByTag(Question question);
}