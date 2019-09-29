package com.wang.novel.dao;

import com.wang.novel.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDao {
    //获取指定名字的书信息
     List<Book> selectBooksByName(String name);
    //获取所有书信息
     List<Book> selectBookAll();
}
