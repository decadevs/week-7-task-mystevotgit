package com.project.dao;

import com.project.model.User;

import java.sql.*;

public class UserDAO {
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
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // create a user
    public String createUser(User user) throws SQLException{
        try {
            String SELECT_USERS_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
            Connection connection = getConnection();
            PreparedStatement sql = connection.prepareStatement(SELECT_USERS_BY_EMAIL);
            sql.setString(1, user.getEmail());
            ResultSet data = sql.executeQuery();
            if (data.next()) {
                String email = data.getString("email");
                if (email.equals(user.getEmail())){
                    return "The email you entered has been registered already";
                }
            }

            String INSERT_USER_SQL = "INSERT INTO users (name, email, birthday, gender, password)" +
                    " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getBirthday().toString());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getPassword());
            statement.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    // get a user id by email
    public Integer getId(String email, String password) {
        int id = 0;
        try {
            String SELECT_USERS_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
            Connection connection = getConnection();
            PreparedStatement sql = connection.prepareStatement(SELECT_USERS_BY_EMAIL);
            sql.setString(1, email);
            ResultSet data = sql.executeQuery();
            if (data.next()) {
                if (password.equals(data.getString("password"))) {
                    id = Integer.parseInt(data.getString("id"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    // update a user
    public boolean updateUser(User user) throws SQLException{
        try {
            Connection connection = getConnection();
            String UPDATE_USERS_SQL = "UPDATE users SET name = ?, email = ?, birthday = ?, gender = ?, " +
                    "password = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getBirthday().toString());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getPassword());
            statement.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // select a user by id
    // select users
    // delete user
}
