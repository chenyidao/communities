package com.community.cyd.service;

import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.mapper.QuestionMapper;
import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.Question;
import com.community.cyd.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    public void create(Question question) {
        questionMapper.create(question);
    }

    //获取question集合，以用于前端展示问题信息（发起人、关注人数、回复数、浏览数等等）
    public List<QuestionDTO> questionList() {
        List<Question>questionList = questionMapper.questionList();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //通过question表中的主键id(问题序号)对应 User中id获取user，然后获取头像地址
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());  //这里有问题，因为数据库设计是允许有两个account_id相同的用户存在，
            QuestionDTO questionDTO = new QuestionDTO();            //所以当question记录对应到两个相同的account_id时，会返回两个user
            BeanUtils.copyProperties(question, questionDTO); //将question属性复制到questionDTO，用于参数传输
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
