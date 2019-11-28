package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Mapper.PersonnelDao;
import com.wang.novelweb.Service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    PersonnelDao personnelDao;

    @Autowired
    public void setPersonnelDao(PersonnelDao personnelDao) {
        this.personnelDao = personnelDao;
    }

    @Override
    public List<String> selectName() {
        return personnelDao.selectName();
    }
}
