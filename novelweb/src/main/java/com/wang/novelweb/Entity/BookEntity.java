package com.wang.novelweb.Entity;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class BookEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 书id
     */
    private String id;
    /**
     * 书名
     */
    private String name;
    /**
     * 书封面存储位置
     */
    private String imageUrl;
    /**
     * 作者
     */
    private String author;
    /**
     * 种类
     */
    private String category;
    /**
     * 最后更新时间
     */
    private Date updateTime;

}
