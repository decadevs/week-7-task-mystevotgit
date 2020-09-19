package com.project.controller;

import com.project.dao.PostDAO;
import com.project.dao.UserDAO;
import com.project.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/Homepage")
public class RegLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("firstname") + " " + request.getParameter("lastname");
        String email = request.getParameter("email").toLowerCase();
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        LocalDate birthday = LocalDate.of(year, month, day);
        String gender = request.getParameter("gender").toLowerCase();
        String password = request.getParameter("password");

        User user = new User(name.toLowerCase(), email, birthday, gender, password);
        try {
            String addUser = new UserDAO().createUser(user);
            if (addUser.equals("success")) {
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                response.sendRedirect("/Posts");
//                request.getRequestDispatcher("/homepage.jsp").forward(request, response);
            }else {
                request.setAttribute("error", addUser);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("mail");
        String password = request.getParameter("pword");
        Integer id = new UserDAO().getId(email.toLowerCase(), password);
        if (id > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("id", id);
            response.sendRedirect("/Posts");
        } else{
            request.setAttribute("err", "Email or Password is incorrect");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
