package com.wang.service.Mapper;

import com.wang.common.entity.novelUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelUserMapper {
    public List<novelUser> getUsers();
}
