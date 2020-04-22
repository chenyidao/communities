package com.community.cyd.mapper;

import com.community.cyd.dto.QuestionQueryDTO;
import com.community.cyd.model.Question;

import java.util.List;

public interface QuestionExtendMapper {
    //自增浏览数
    int incViewCount(Question question);
    //自增评论数
    int incCommentCount(Question question);
    //获取关联问题
    List<Question> selectRelatedByTag(Question question);
    //获取满足查询的问题数
    Integer countBySearch(QuestionQueryDTO questionQueryDTO);
    //获取满足条件的问题列表
    List<Question> selectBySearch(QuestionQueryDTO queryDTO);
}