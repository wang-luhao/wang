package com.wang.novel.service.impl;

import com.wang.novel.dao.BookDao;
import com.wang.novel.entity.Book;
import com.wang.novel.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {


    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getAllBooks() {

        return  bookDao.selectBookAll();
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return bookDao.selectBooksByName(name);
    }
}
