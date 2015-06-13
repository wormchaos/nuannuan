package com.wormchaos.service;

import com.wormchaos.dao.entity.User;
import com.wormchaos.dao.persistence.UserMapper;
import com.wormchaos.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wormchaos on 2015/6/13.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public User findUser(String username, String password) {
        return userMapper.findUser(username, password);
    }

    @Override
    public void createUser(String username, String password) {
        userMapper.createUser(username, password);
    }
}
