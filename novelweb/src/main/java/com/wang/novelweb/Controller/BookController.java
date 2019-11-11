package com.wang.novelweb.Controller;

import java.util.Arrays;
import java.util.Map;

import com.wang.novelweb.Service.BookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("generator/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
/**
 * 列表
 */


    /**
     * 信息
     */


    /**
     * 保存
     */


    /**
     * 修改
     */


    /**
     * 删除
     */

}
