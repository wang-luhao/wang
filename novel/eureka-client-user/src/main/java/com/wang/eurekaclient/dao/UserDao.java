package com.wang.eurekaclient.dao;

import com.wang.eurekaclient.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    int insert(User record);

    int insertSelective(User record);

    User select(Long userId);
}