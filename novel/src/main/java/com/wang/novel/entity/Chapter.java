package com.wang.novel.entity;

public class Chapter {
    private int book_id;//书编号
    private int id;//章节编号
    private String name;//章节名
    private String url;//章节存放地址

    public Chapter(int book_id, int id, String name, String url) {
        this.book_id = book_id;
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
