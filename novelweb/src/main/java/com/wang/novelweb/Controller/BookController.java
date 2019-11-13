package com.wang.novelweb.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wang.novelweb.Entity.BookEntity;
import com.wang.novelweb.Service.BookService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    /**
     * 无条件列表查询
     */
    @ResponseBody
    @RequestMapping(value = "bookListsAll", method = RequestMethod.GET)
    @RequiresPermissions("user1")
    public Map bookLists(){
        Map<String,Object> resultMap = new HashMap<>();
        List<BookEntity> bookLists = bookService.bookLists();
        resultMap.put("bookLists",bookLists);
        return resultMap;
    }
    @ResponseBody
    @RequestMapping(value = "bookLists", method = RequestMethod.GET)
    @RequiresPermissions("user")
    public Map bookLists(Integer pageNum,Integer pageSize){
        Map<String,Object> resultMap = new HashMap<>();
        int bookCount = bookService.bookCount();
        List<BookEntity> bookLists = bookService.bookLists(pageNum,pageSize);
        resultMap.put("bookCount",bookCount);
        resultMap.put("pageCount",bookCount/pageSize+1);
        resultMap.put("pageNum",pageNum);
        resultMap.put("pageSize",pageSize);
        resultMap.put("bookLists",bookLists);
        return resultMap;
    }
}
