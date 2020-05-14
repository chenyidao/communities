package com.community.cyd.service;

import com.community.cyd.dto.ConsultDTO;
import com.community.cyd.mapper.ConsultMapper;
import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.Consult;
import com.community.cyd.model.User;
import com.community.cyd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConsultService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConsultMapper consultMapper;

    /*获取可咨询用户列表*/
    public List<ConsultDTO> getConsultList(Long userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdNotEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        List<ConsultDTO> dtoList = new LinkedList<>();
        for (int i = 0; i < users.size(); i++) {
            ConsultDTO consultDTO = new ConsultDTO();
            consultDTO.setConsultFee(500L + i * 10);
            consultDTO.setUser(users.get(i));
            dtoList.add(consultDTO);
        }
        return dtoList;
    }

    /*插入咨询记录*/
    public void insertRecord(Consult consult) {
        consultMapper.insert(consult);
    }
}
