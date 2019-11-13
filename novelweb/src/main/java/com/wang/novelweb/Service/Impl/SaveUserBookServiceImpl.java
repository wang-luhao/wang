package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.SaveUserBookEntity;
import com.wang.novelweb.Mapper.SaveUserBookDao;
import com.wang.novelweb.Service.SaveUserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("saveUserBookService")
public class SaveUserBookServiceImpl implements SaveUserBookService {

    private SaveUserBookDao saveUserBookDao;

    @Autowired
    public void setSaveUserBookDao(SaveUserBookDao saveUserBookDao) {
        this.saveUserBookDao = saveUserBookDao;
    }

    @Override
    public List<SaveUserBookEntity> saveUserBookLists(Integer userId) {
        return saveUserBookDao.saveUserBookLists(userId);
    }
}