package com.project.model;

import java.util.Date;

public class Comment {
    private Date date;
    private String text;
    private Integer like;
    private User user;

    public Comment(User user, String text) {
        this.date = new Date();
        this.text = text;
        this.user = user;
        this.like = 0;
    }
}
