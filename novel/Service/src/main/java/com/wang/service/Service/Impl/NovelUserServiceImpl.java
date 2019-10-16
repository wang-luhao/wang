package com.wang.service.Service.Impl;

import com.wang.common.entity.novelUser;
import com.wang.service.Mapper.NovelUserMapper;
import com.wang.service.Service.NovelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NovelUserServiceImpl implements NovelUserService {

    private NovelUserMapper novelUserMapper;

    @Autowired
    public void setNovelUserMapper(NovelUserMapper novelUserMapper) {
        this.novelUserMapper = novelUserMapper;
    }

    @Override
    public List<novelUser> getUsers() {
        return novelUserMapper.getUsers();
    }
}
