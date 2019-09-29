package com.wang.novel.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.novel.common.MSG;
import com.wang.novel.entity.Book;
import com.wang.novel.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@ResponseBody
public class BookController {

    private IBookService bookService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/allBooks")
    public MSG allBooks(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageNum",defaultValue = "3") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Book> books = bookService.getAllBooks();
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        return  MSG.success().add("pageInfo",pageInfo);
    }

    @RequestMapping("/getBooks")
    public MSG booksByName(@RequestParam("name") String name){
        return MSG.success().add("books",bookService.getBooksByName(name));
    }
}
