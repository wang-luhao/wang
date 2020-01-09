package com.wang.novelweb.Service.Impl;

import com.wang.novelweb.Entity.ChapterEntity;
import com.wang.novelweb.Mapper.ChapterDao;
import com.wang.novelweb.Service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    private ChapterDao chapterDao;

    @Autowired
    public void setChapterDao(ChapterDao chapterDao) {
        this.chapterDao = chapterDao;
    }

    @Override
    public List<ChapterEntity> chapterLists(Integer id) {
        return chapterDao.chapterLists(id);
    }

    @Override
    public Integer chapterCount(Integer id) {
        return chapterDao.chapterCount(id);
    }

    @Override
    public ChapterEntity chapter(Integer chapterId) {
        return chapterDao.chapter(chapterId);
    }
}