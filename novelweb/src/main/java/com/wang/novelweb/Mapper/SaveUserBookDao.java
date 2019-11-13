package com.wang.novelweb.Mapper;


import com.wang.novelweb.Entity.SaveUserBookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaveUserBookDao {
    List<SaveUserBookEntity> saveUserBookLists(Integer userId);
}
