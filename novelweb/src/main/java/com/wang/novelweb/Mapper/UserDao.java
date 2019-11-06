package com.wang.novelweb.Mapper;


import com.wang.novelweb.Entity.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {
    UserEntity findUser(String  username);
}
