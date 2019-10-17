package com.wang.novelweb.Mapper;

import com.wang.novelweb.Entity.NovelUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface NovelUserMapper {
    public NovelUser  loginCheck(Map<String, String> log);

}
