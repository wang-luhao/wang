package com.wang.novel.controller;

import com.wang.novel.common.MSG;
import com.wang.novel.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ChapterController {
    private IChapterService chapterService;

    @Autowired
    public void setChapterService(IChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @RequestMapping("/chapters")
    public MSG allChapter(@RequestParam("book_id") int book_id) {
        return MSG.success().add("chapters",chapterService.getChapters(book_id));
    }

    @RequestMapping("/chapter")
    public MSG getChapter(@RequestParam("id") int id) {
        return MSG.success().add("chapter",chapterService.getChapterURL(id));
    }
}
