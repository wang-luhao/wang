package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.BookEntity;
import com.wang.novelweb.Mapper.BookDao;
import com.wang.novelweb.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bookService")
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookEntity> bookLists() {
        return null;
    }

    @Override
    public List<BookEntity> bookLists(Integer pageNum, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("pageIndex",(pageNum-1)*pageSize);
        map.put("pageSize",pageSize);
        return bookDao.bookLists(map);
    }

    @Override
    public Integer bookCount() {
        return bookDao.bookCount();
    }

    @Override
    public BookEntity bookInfoById(Integer id) {
        return bookDao.bookInfoById(id);
    }
}