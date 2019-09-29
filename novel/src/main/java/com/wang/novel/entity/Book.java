package com.wang.novel.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Book {
    private int id;//书编号
    private String name;//书名
    private String author;//作者
    private int chapters;

    public Book(int id, String name, String author, int chapters) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.chapters = chapters;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapters() {
        return chapters;
    }

    public void setChapters(int chapters) {
        this.chapters = chapters;
    }
}
