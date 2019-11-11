package com.wang.novelweb.Mapper;


import com.wang.novelweb.Entity.BookEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookDao {
    List<BookEntity> bookLists();
}
