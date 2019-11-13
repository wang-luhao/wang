package com.wang.novelweb.Mapper;


import com.wang.novelweb.Entity.SaveUserBookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface SaveUserBookDao {
    List<SaveUserBookEntity> saveUserBookLists(Integer userId);
    Integer updateSaveUserBookChapter(Map map);
    Integer updateSaveUserBookIsSave(Map map);
    Integer selectIsSave(Map map);
}
