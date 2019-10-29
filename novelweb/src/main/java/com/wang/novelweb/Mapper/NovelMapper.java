package com.wang.novelweb.Mapper;

import com.wang.novelweb.Entity.Novel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelMapper {
    public int addNovel(@Param("novelList") List<Novel> list);
}
