package com.community.cyd.service;

import com.community.cyd.mapper.UserMapper;
import com.community.cyd.model.User;
import com.community.cyd.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByToken(String token) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }

    public void insertOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null) {
            //插入
            userMapper.insert(user);
        } else {
            //更新
            User dbUser = users.get(0);
            dbUser.setToken(user.getToken());   //换token标识
            dbUser.setAvatarUrl(user.getAvatarUrl());   //换头像
            dbUser.setGmtModified(System.currentTimeMillis());  //更改时间
            dbUser.setName(user.getName());  //改名
//            UserExample userExample = new UserExample();

            userMapper.updateByPrimaryKey(dbUser);
        }
    }
}
