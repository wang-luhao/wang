package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Mapper.UserDao;
import com.wang.novelweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity findUser(String username) {
        return userDao.findUser(username);
    }
}