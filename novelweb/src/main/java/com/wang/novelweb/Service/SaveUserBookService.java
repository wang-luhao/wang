package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.SaveUserBookEntity;

import java.util.List;

public interface SaveUserBookService {
    List<SaveUserBookEntity> saveUserBookLists(Integer userId);
}

