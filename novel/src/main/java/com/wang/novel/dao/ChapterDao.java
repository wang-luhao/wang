package com.wang.novel.dao;

import com.wang.novel.entity.Chapter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterDao {
    //根据书号查找所有章节
    public List<Chapter> selectChapterByBookId(int book_id);
    //根据章节号查找该章节的URL
    public String selectChapterById(int id);
}
