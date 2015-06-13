package com.wormchaos.service.base;

import com.wormchaos.dao.entity.User;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface UserService {

    User findUserByName(String username);

    User findUser(String username, String password);

    void createUser(String username, String password);
}
