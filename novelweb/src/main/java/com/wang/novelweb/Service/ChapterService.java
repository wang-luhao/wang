package com.wang.novelweb.Service;


import com.wang.novelweb.Entity.ChapterEntity;

import java.util.List;

public interface ChapterService {
    public List<ChapterEntity> chapterLists(Integer id);
    Integer chapterCount(Integer id);
}

