package com.wang.novelweb.Mapper;


import com.wang.novelweb.Entity.ChapterEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChapterDao {
    List<ChapterEntity>chapterLists(Integer id);
    Integer chapterCount(Integer id);
    ChapterEntity chapter(Integer chapterId);
}
