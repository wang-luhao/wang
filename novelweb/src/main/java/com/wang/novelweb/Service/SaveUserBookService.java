package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.BookEntity;
import com.wang.novelweb.Entity.SaveUserBookEntity;

import java.util.List;
import java.util.Map;

public interface SaveUserBookService {
    Integer updateSaveUserBookChapter(Map map);

    Integer updateSaveUserBookIsSave(Map map);

    Integer selectIsSave(Integer bookId, Integer userId);

    List<BookEntity> selectSaveBooks(Integer userId);
}

