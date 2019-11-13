package com.wang.novelweb.Controller;


import com.wang.novelweb.Entity.ChapterEntity;
import com.wang.novelweb.Service.BookService;
import com.wang.novelweb.Service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/chapter")
public class ChapterController {

    private ChapterService chapterService;
    private BookService bookService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/chapterLists")
    public Map chapterLists(Integer id){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("bookInfo",bookService.bookInfoById(id));
        resultMap.put("chapterCount",chapterService.chapterCount(id));
        resultMap.put("chapterLists", chapterService.chapterLists(id));
        return resultMap;
    }

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
