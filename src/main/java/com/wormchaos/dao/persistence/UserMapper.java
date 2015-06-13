package com.wormchaos.dao.persistence;

import com.wormchaos.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wormchaos on 2015/6/13.
 */
public interface UserMapper {

    User findUserByName(@Param("username") String username);

    User findUser(@Param("username") String username, @Param("password") String password);

    void createUser(@Param("username") String username, @Param("password") String password);
}
