package com.wang.novelweb.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wang.novelweb.Entity.BookEntity;
import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.BookService;

import com.wang.novelweb.Service.ChapterService;
import com.wang.novelweb.Service.SaveUserBookService;
import com.wang.novelweb.Service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Log4j
@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private ChapterService chapterService;
    private UserService userService;
    private SaveUserBookService saveUserBookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setSaveUserBookService(SaveUserBookService saveUserBookService) {
        this.saveUserBookService = saveUserBookService;
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

    @ResponseBody
    @RequestMapping("bookInfo")
   // @RequiresPermissions("user")
    public Map bookInfo(Integer bookId){
        UserEntity user = (UserEntity)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("bookInfo",bookService.bookInfoById(bookId));
        resultMap.put("bookChapters",chapterService.chapterLists(bookId));
        resultMap.put("bookIsSave", saveUserBookService.selectIsSave(bookId,user.getId()));
        return resultMap;
    }
}
