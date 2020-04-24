package com.community.cyd.service;

import com.community.cyd.dto.PaginationDTO;
import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.dto.QuestionQueryDTO;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.exception.CustomizeException;
import com.community.cyd.mapper.QuestionExtendMapper;
import com.community.cyd.mapper.QuestionMapper;
import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.Question;
import com.community.cyd.model.QuestionExample;
import com.community.cyd.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    /**
     * 获取question集合，以用于前端展示问题信息（发起人、关注人数、回复数、浏览数等等），并分页
     **/

    public PaginationDTO questionList(Integer page, String tag, Integer size, String search) {
        if (StringUtils.isNotBlank(search)) {
            String[] searchArray = StringUtils.split(search, " ");
            search = Arrays.stream(searchArray).collect(Collectors.joining("|"));
        }
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setPage(page);
        questionQueryDTO.setSize(size);
        questionQueryDTO.setSearch(search);
        questionQueryDTO.setTag(tag);
        //通过search条件筛选总条数
        Integer totalCount = questionExtendMapper.countBySearch(questionQueryDTO);
        questionQueryDTO.setTotalCount(totalCount);
        PaginationDTO paginationDTO = getQuestionList(questionQueryDTO);
        return paginationDTO;
    }

    /**
     * 通过userId获取该user发布的问题(与question中的creator关联)
     **/
    public PaginationDTO getListByUserId(Long userId, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setPage(page);
        questionQueryDTO.setSize(size);
        questionQueryDTO.setTotalCount(totalCount);
        PaginationDTO paginationDTO = getQuestionList(questionQueryDTO);
        return paginationDTO;
    }

    /**
     * 获取question集合 重构核心函数
     **/
    public PaginationDTO getQuestionList(QuestionQueryDTO queryDTO) {
        PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<>();
        Integer totalPage;
        Integer totalCount = queryDTO.getTotalCount();
        Integer size = queryDTO.getSize();
        Integer page = queryDTO.getPage();

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
        queryDTO.setPage(offset);  //到这里page已经没用了，索性将page的值设置为offset，用于分页
        List<Question> questionList = questionExtendMapper.selectBySearch(queryDTO);
        //通过question表中的主键id(问题序号)对应 User中id获取user，然后获取头像地址
        List<QuestionDTO> questionDTOS = questionList.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            User user = userMapper.selectByPrimaryKey(q.getCreator());
            BeanUtils.copyProperties(q, questionDTO);
            questionDTO.setUser(user);
            return questionDTO;
        }).collect(Collectors.toList());
        paginationDTO.setData(questionDTOS);
        return paginationDTO;
    }

    /**
     * 通过id获取该questionDTO
     **/
    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 创建或者更新问题
     **/
    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //插入
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        } else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            int updated = questionMapper.updateByPrimaryKey(question); //返回0则失败，返回1则成功
            if (updated == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    /**
     * 访问次数
     **/
    public void incViewCount(Long id) {
        Question question = new Question();
        question.setId(id);         //因为每次通过id只更新viewCount，所以不需要赋其他属性的值。
        question.setViewCount(1);   //递增步长为1
        questionExtendMapper.incViewCount(question);
    }

    /**
     * 通过tag获取关联问题
     */
    public List<QuestionDTO> selectRelatedByTag(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ',');
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));

        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtendMapper.selectRelatedByTag(question);

        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}
