package com.project.model;

import java.util.Date;

public class Comment {
    private Date date;
    private String text;
    private Integer like;
    private int userid;

    public Comment(int userid, String text) {
        this.date = new Date();
        this.text = text;
        this.userid = userid;
        this.like = 0;
    }
}
