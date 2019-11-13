package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.SaveUserBookEntity;
import com.wang.novelweb.Mapper.SaveUserBookDao;
import com.wang.novelweb.Service.SaveUserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;


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

    @Override
    public Integer updateSaveUserBookChapter(Map map) {
        return saveUserBookDao.updateSaveUserBookChapter(map);
    }

    @Override
    public Integer updateSaveUserBookIsSave(Map map) {
        return saveUserBookDao.updateSaveUserBookIsSave(map);
    }

    @Override
    public Integer selectIsSave(Integer bookId, Integer userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("userId",userId);
        return saveUserBookDao.selectIsSave(map);
    }
}