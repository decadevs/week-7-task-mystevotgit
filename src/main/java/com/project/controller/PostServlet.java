package com.project.controller;

import com.project.dao.PostDAO;
import com.project.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

//@WebServlet("/Posts")
public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("dfdfdfdf======" + path);
        switch(path) {
            case "/Create_post":
                this.createPost(request, response);
                break;
            case "/Update_post":
                try {
                    this.updatePost(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/Delete_post":
                try {
                    this.deletePost(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/Like_post":
                try {
                    this.likePost(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/Dislike_post":
                try {
                    this.dislikePost(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

        }

    }

    private void dislikePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String likeId = request.getParameter("like_id");
        ResultSet executed = new PostDAO().dislikePost(likeId);
        response.sendRedirect("/Posts");
    }

    private void likePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String postId = request.getParameter("post_id");
        var likes = request.getAttribute("likes");
        boolean executed = new PostDAO().likePost(postId, (Integer) likes);
        response.sendRedirect("/Posts");
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String postId = request.getParameter("post_id");
        boolean executed = new PostDAO().deletePost(postId);
        response.sendRedirect("/Posts");
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String text = request.getParameter("text");
        String postId = request.getParameter("post_id");
        System.out.println(postId);
        HttpSession session = request.getSession();
        var id = session.getAttribute("id");
        System.out.println(id);
        Post post = new Post((Integer) id, text);
        post.setId(Integer.parseInt(postId));
        boolean executed = new PostDAO().updatePost(post);
        response.sendRedirect("/Posts");
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String text = request.getParameter("text");
            HttpSession session = request.getSession();
            var id = session.getAttribute("id");
            System.out.println(id);
            Post post = new Post((Integer) id, text);
            boolean executed = new PostDAO().addPost(post);
            response.sendRedirect("/Posts");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println("dfdfdfdf======" + path);
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        var id = session.getAttribute("id");
        System.out.println(id);
        if (email != null ) {
            ResultSet data = null;
            ResultSet likedata = null;
            ResultSet likes = null;
            try {
                data = new PostDAO().getPosts(id.toString());
                likedata = new PostDAO().getLikedata(data.getString("id"));
                likes = new PostDAO().getLikes(data.getString("id"));
                request.setAttribute("data", data);
                request.setAttribute("email", email);
                request.setAttribute("likedata", likedata);
                request.setAttribute("likes", likes);
                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            request.setAttribute("isLoggedin", "Please sign in to continue");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

