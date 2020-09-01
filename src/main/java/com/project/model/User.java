package com.project.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private String email;
    private LocalDate birthday;
    private String gender;
    private String password;

    public User(String name, String email, LocalDate birthday, String gender, String password) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
