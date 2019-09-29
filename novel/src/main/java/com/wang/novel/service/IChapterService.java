package com.wang.novel.service;

import com.wang.novel.entity.Chapter;

import java.util.List;

/**
 *  //根据书号查找所有章节
 *     public List<Chapter> selectChapterByBookId(int book_id);
 *     //根据章节号查找该章节的URL
 *     public String selectChapterById(int id);
 */
public interface IChapterService {
    //根据书号查找所有章节
     List<Chapter> getChapters(int book_id);
    //根据章节号查找该章节的URL
     String getChapterURL(int id);
}
