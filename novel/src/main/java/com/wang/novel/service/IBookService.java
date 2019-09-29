package com.wang.novel.service;

import com.wang.novel.entity.Book;

import java.util.List;

public interface IBookService {
    //获取所有书信息
     List<Book> getAllBooks();
    //获取指定名字的书信息
     List<Book> getBooksByName(String name);
}
