package com.project.dao;

import com.project.model.Post;

import java.sql.*;


public class PostDAO {
    private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/facebook";
    private String jdbcusername = "root";
    private String jdbcpassword = "database";

    private String SELECT_ALL_USERS = "SELECT * FROM users";
    private String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, jdbcusername, jdbcpassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // get the posts of the user
    public ResultSet getPosts(String id) throws SQLException {
        String SELECT_USERS_BY_ID = "SELECT * FROM posts ORDER BY post_date DESC, id DESC";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(SELECT_USERS_BY_ID);
        ResultSet data = sql.executeQuery();
        return data;
    }

    // Add a user post
    public boolean addPost(Post post) throws SQLException {
        String INSERT_A_POST = "INSERT INTO posts (user_id, post_date, post_text) VALUES (?, ?, ?);";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(INSERT_A_POST);
        sql.setString(1, post.getUser_id());
        sql.setString(2, post.getDate());
        sql.setString(3, post.getText());
        boolean result = sql.execute();
        String SELECT_POSTID = "SELECT id FROM posts WHERE user_id = ? ORDER BY post_date DESC, id DESC";
        PreparedStatement postid = connection.prepareStatement(SELECT_POSTID);
        postid.setString(1, post.getUser_id());
        ResultSet post_id = postid.executeQuery();
        String CREATE_POSTLIKE = "INSERT INTO likes (user_id, post_id, likecount) VALUES (?, ?, ?)";
        PreparedStatement sqlikes = connection.prepareStatement(CREATE_POSTLIKE);
        sqlikes.setString(1, post.getUser_id());
        sqlikes.setString(2, String.valueOf(post_id.next()));
        sqlikes.setString(3, "0");
        return result;
    }

    public boolean updatePost(Post post) throws SQLException {
        String UPDATE_A_POST = "UPDATE posts SET post_text = ? WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(UPDATE_A_POST);
        sql.setString(1, post.getText());
        sql.setString(2, post.getId());
        boolean updatedPost_data = sql.execute();
        return updatedPost_data;
    }

    public boolean deletePost(String postId) throws SQLException {
        String DELETE_A_POST = "DELETE FROM posts WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(DELETE_A_POST);
        sql.setString(1, postId);
        boolean updatedPost_data = sql.execute();
        return updatedPost_data;
    }

    public boolean likePost(String postId, Integer current) throws SQLException {
        String LIKE_A_POST = "UPDATE post_likes SET like_count = ? WHERE post_id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(LIKE_A_POST);
        sql.setString(1, String.valueOf(current + 1));
        sql.setString(2, postId);
        boolean result = sql.execute();
        return result;
    }

    public ResultSet dislikePost(String likeId) throws SQLException {
        String DISLIKE_A_POST = "DELETE FROM post_likes WHERE id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(DISLIKE_A_POST);
        sql.setString(1, likeId);
        ResultSet updatedPost_data = sql.executeQuery();
        return updatedPost_data;
    }

    public ResultSet getLikedata(String id) throws SQLException {
        String SELECT_POSTLIKES_BY_POSTID = "SELECT * FROM post_likes WHERE post_id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(SELECT_POSTLIKES_BY_POSTID);
        sql.setString(1, id);
        ResultSet data = sql.executeQuery();
        return data;
    }

    public ResultSet getLikes(String id) throws SQLException {
        String SELECT_NO_OF_LIKES = "SELECT like_count FROM likes WHERE post_id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(SELECT_NO_OF_LIKES);
        sql.setString(1, id);
        ResultSet data = sql.executeQuery();
        return data;
    }
}

