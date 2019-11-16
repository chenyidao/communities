package com.community.cyd.service;

import com.community.cyd.dto.PaginationDTO;
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

    //获取question集合，以用于前端展示问题信息（发起人、关注人数、回复数、浏览数等等），并分页
    public PaginationDTO questionList(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPaginationDTO(totalPage, page); //设置返回前端显示的内容

        /**
         * 问题：当page < 0 时 以及 page > totalPage时 查询不到questionList ???
         **/
        Integer offset = size * (page - 1);    //select * from question limit offset,size 获取偏移量

        List<Question> questionList = questionMapper.questionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //通过question表中的主键id(问题序号)对应 User中id获取user，然后获取头像地址
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //将question属性复制到questionDTO，用于参数传输
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    //通过userId获取该user发布的问题(与question中的creator关联)
    public PaginationDTO getListByUserId(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.getCountByUserId(userId);
        Integer totalPage;

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPaginationDTO(totalPage, page); //设置返回前端显示的内容

        Integer offset = size * (page - 1);    //select * from question limit offset,size 获取偏移量

        List<Question> questionList = questionMapper.getListByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        //通过question表中的主键id(问题序号)对应 User中id获取user，然后获取头像地址
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //将question属性复制到questionDTO，用于参数传输
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
