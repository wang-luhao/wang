package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.BookEntity;
import com.wang.novelweb.Mapper.BookDao;
import com.wang.novelweb.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("bookService")
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookEntity> bookLists() {
        return bookDao.bookLists();
    }
}