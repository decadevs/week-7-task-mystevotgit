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


public class PostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        // This handles the various post request from the browser.
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

    /**
     * This method is used to handle the dislike of a post.
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void dislikePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String postId = request.getParameter("post_id");
        var likes = request.getParameter("likes");
        if (postId != null && likes != null) {
            boolean executed = new PostDAO().dislikePost(postId, Integer.valueOf(likes));
            response.sendRedirect("/Posts");
        }
    }

    /**
     * The method is used to handle the liking of a post.
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    private void likePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String postId = request.getParameter("post_id");

        if (postId != null && !postId.equals("0")) {
            boolean executed = new PostDAO().likePost(postId);
            response.sendRedirect("/Posts");
        }
    }

    /**
     * This method is used to handle the delete of a post
     * @param request
     * @param response
     * @throws SQLException
     * @throws IOException
     */
    private void deletePost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String postId = request.getParameter("post_id");
        boolean executed = new PostDAO().deletePost(postId);
        response.sendRedirect("/Posts");
    }

    /**
     * This method is used to hancle the update of a post
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String text = request.getParameter("text");
        String postId = request.getParameter("post_id");

        HttpSession session = request.getSession();
        var id = session.getAttribute("id");

        Post post = new Post((Integer) id, text);
        post.setId(Integer.parseInt(postId));
        boolean executed = new PostDAO().updatePost(post);
        response.sendRedirect("/Posts");
    }

    /**
     * This method is used to handle the creation of a post.
     * @param request
     * @param response
     */
    private void createPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String text = request.getParameter("text");
            HttpSession session = request.getSession();
            var id = session.getAttribute("id");

            Post post = new Post((Integer) id, text);
            boolean executed = new PostDAO().addPost(post);
            response.sendRedirect("/Posts");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * The doget method is used to all the get request to the Posts servelet.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        var id = session.getAttribute("id");

        if (email != null ) {
            ResultSet data = null;
            try {
                data = new PostDAO().getPosts(id.toString());

                if (data != null) {


                    request.setAttribute("data", data);
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/homepage.jsp").forward(request, response);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else {
            request.setAttribute("isLoggedin", "Please sign in to continue");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}

