package com.wang.novelweb.Entity;


import java.io.Serializable;

import lombok.Data;


@Data
public class SaveUserBookEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 书id
     */

    private String bookId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 当前用户阅读章节的id
     */
    private String chapterId;
    /**
     * 用户是否收藏该书，1收藏，0未收藏
     */
    private Integer isSave;

}
