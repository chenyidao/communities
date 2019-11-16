package com.community.cyd.service;

import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

    public void insertOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            //插入
            userMapper.insert(user);
        } else {
            //更新
            dbUser.setToken(user.getToken());   //换token标识
            dbUser.setAvatarUrl(user.getAvatarUrl());   //换头像
            dbUser.setGmtModified(System.currentTimeMillis());  //更改时间
            dbUser.setName(user.getName());  //改名
            userMapper.update(dbUser);
        }
    }
}
