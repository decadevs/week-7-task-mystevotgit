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
    public ResultSet getPosts(Integer user_id) throws SQLException {
        String SELECT_USERS_BY_ID = "SELECT * FROM posts WHERE user_id = ?";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(SELECT_USERS_BY_ID);
        sql.setString(1, user_id.toString());
        ResultSet data = sql.executeQuery();
        return data;
    }

    // Add a user post
    public void addPost(Post post) throws SQLException {
        String INSERT_A_POST = "INSERT INTO posts (user_id, post_date, post_text) VALUES (?, ?, ?);";
        Connection connection = getConnection();
        PreparedStatement sql = connection.prepareStatement(INSERT_A_POST);
        sql.setString(1, post.getUser_id());
        sql.setString(2, post.getDate());
        sql.setString(3, post.getText());
        sql.executeQuery();
    }

}

