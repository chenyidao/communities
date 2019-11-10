package com.community.cyd.service;

import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    public void insert(User user){
        System.out.println(user);
        userMapper.insert(user);
    }
}
