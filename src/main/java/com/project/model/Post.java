package com.project.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post {
    private Date date;
    private String text;
    private Integer like;
    private Map<User, Comment> comments;

    public Post(String text) {
        this.date = new Date();
        this.text = text;
        this.like = 0;
        this.comments = new HashMap<>();
    }

    //  Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Map<User, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<User, Comment> comments) {
        this.comments = comments;
    }
}
