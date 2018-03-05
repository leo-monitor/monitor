package com.monitor.service;

import com.monitor.dao.UserDao;
import com.monitor.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/23.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User selectByUsername(@Param("username") String username) {
        return userDao.selectByUsername(username);
    }


    public int insertUser(@Param("username") String username, @Param("password") String password, @Param("user_type") int userType) {
        User entity = new User();
        entity.setUsername(username);
        entity.setPassword(password);
        entity.setUserType(userType);
        entity.setCreatedDate(new Date());
        return userDao.insertUser(entity);
    }
}
