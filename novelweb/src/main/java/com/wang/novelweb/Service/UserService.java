package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.UserEntity;

public interface UserService {


    UserEntity findUser(String username);

    Integer findUserId(String username);
}

