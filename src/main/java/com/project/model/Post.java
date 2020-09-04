package com.project.model;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Post {
    private int id;
    private int user_id;
    private Date date;
    private String text;
    private int likes;
    private List<Comment> comments;

    /**
     * This is the post contructor
     * @param user_id
     * @param text
     */

    public Post(int user_id, String text) {
        this.user_id = user_id;
        this.date = new Date();
        this.text = text;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    //  Getters and Setters
    public String getDate() {
        String res = "";
        String str = String.valueOf(date);
        List<String> list = Arrays.asList(str.split(" "));
        res += list.get(list.size()-1);
        res += "-";
        String month;
        String val = list.get(1);
        switch (val) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
            default:
                month = "12";
                break;
        }
        res += month;
        res += "-";
        res += list.get(2);
        System.out.println(res);
        return res;
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
        return likes;
    }

    public Integer like() {
        return this.likes += 1;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return String.valueOf(user_id);
    }
}
