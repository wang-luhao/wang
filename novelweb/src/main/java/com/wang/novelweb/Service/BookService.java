package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.BookEntity;

import java.util.List;

public interface BookService {
    List<BookEntity> bookLists();

    List<BookEntity> bookLists(Integer pageNum, Integer pageSize);

    Integer bookCount();

    BookEntity bookInfoById(Integer id);

}

