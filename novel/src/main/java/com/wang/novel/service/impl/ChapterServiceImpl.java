package com.wang.novel.service.impl;

import com.wang.novel.dao.ChapterDao;
import com.wang.novel.entity.Chapter;
import com.wang.novel.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements IChapterService {

    private ChapterDao chapterDao;

    @Autowired
    public void setChapterDao(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    @Override
    public List<Chapter> getChapters(int book_id) {
        return chapterDao.selectChapterByBookId(book_id);
    }

    @Override
    public String getChapterURL(int id) {
        return chapterDao.selectChapterById(id);
    }
}
