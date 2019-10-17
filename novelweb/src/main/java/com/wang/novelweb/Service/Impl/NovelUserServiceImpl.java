package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.NovelUser;
import com.wang.novelweb.Mapper.NovelUserMapper;
import com.wang.novelweb.Service.NovelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NovelUserServiceImpl implements NovelUserService {


    private NovelUserMapper novelUserMapper;
    @Autowired
    public void setNovelUserMapper(NovelUserMapper novelUserMapper) {
        this.novelUserMapper = novelUserMapper;
    }

    @Override
    public NovelUser LoginCheck(String phone, String password) {
        Map<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",password);

        return novelUserMapper.loginCheck(map);
    }
}
