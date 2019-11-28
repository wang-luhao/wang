package com.wang.novelweb.Entity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class ChapterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节id
     */

    private String id;
    /**
     * 章节名
     */
    private String name;
    /**
     * 章节存储位置
     */
    private String url;

    /**
     * 对应的书籍id
     */
    private String bookId;

    /**
     * 对应的书籍名称
     */
    private String bookName;

    /**
     * 最后更新时间
     */
    private Date updateTime;
}
