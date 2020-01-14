package com.wang.eurekaclient.service.impl;

import com.wang.eurekaclient.dao.UserDao;
import com.wang.eurekaclient.entity.User;
import com.wang.eurekaclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author  wlh
 * date  2020/1/11 0011 10:14
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User select(Long userId) {
//        return userDao.select(userId);
        return new User("1","name","password");
    }
}
