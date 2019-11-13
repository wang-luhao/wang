package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.SaveUserBookEntity;

import java.util.List;
import java.util.Map;

public interface SaveUserBookService {
    List<SaveUserBookEntity> saveUserBookLists(Integer userId);
    Integer updateSaveUserBookChapter(Map map);
    Integer updateSaveUserBookIsSave(Map map);
    Integer selectIsSave(Integer bookId,Integer userId);
}

