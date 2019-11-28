package com.wang.novelweb.Mapper;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelDao {
    List<String> selectName();
}
