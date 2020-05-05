package com.community.cyd.service;

import com.community.cyd.dto.ConsultDTO;
import com.community.cyd.mapper.UserMapper;
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

    public List<ConsultDTO> getConsult() {
        List<User> users = userMapper.selectByExample(new UserExample());
        List<ConsultDTO> dtoList = new LinkedList<>();
        for (User user : users) {
            ConsultDTO consultDTO = new ConsultDTO();
            consultDTO.setOutTradeNo("no123");
            consultDTO.setTotal_amount(500L);
            consultDTO.setSubject("测试订单");
            consultDTO.setDescription("支付宝沙箱测试订单描述");
            consultDTO.setUser(user);
            dtoList.add(consultDTO);
        }
        return dtoList;
    }
}
