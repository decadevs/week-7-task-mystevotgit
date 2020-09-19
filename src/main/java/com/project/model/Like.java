package com.project.model;

public class Like {
    private int user_id;
    private int post_id;
    private int like_count;


    /**
     * this is the like constructor.
     * @param user_id
     * @param post_id
     * @param like_count
     */
    public Like(int user_id, int post_id, int like_count) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.like_count = like_count;
    }


    // getters and setters
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
