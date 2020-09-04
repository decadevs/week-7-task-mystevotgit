<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 8/30/20
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<html>
<head>
    <title>Facebook - Home</title>
    <meta charset="utf-8">
    <link href='style.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="client.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<header>
    <div class="logo">facebook</div>
    <div class="container">
        <%
            String email = (String) request.getAttribute("email");
            out.print("<h3>");
            out.print(email);
            out.print("</h3>");
            out.print("<form action='/Login' method='get'><input type='submit' value='Log Out' class='btn'></form>");
        %>
    </div>
</header>
<div class="contain">
    <div class="body">
        <br>
        <h2>Write a post</h2>
        <form action="Create_post" method="post">
            <textarea class="text-post" name="text" id="text" cols="30" rows="10"></textarea>
            <input type="submit" value="Publish Post" class="btn">
        </form>
        <br>
        <h2>Posts</h2>
        <%
            ResultSet data = (ResultSet) request.getAttribute("data");
//            ResultSet likedata = (ResultSet) request.getAttribute("likedata");
//            ResultSet likes = (ResultSet) request.getAttribute("likes");
            if (data == null) {
                out.print("<div><h5>You do not have any post yet... You can create a new post now.</h5></div>");
            }else {
                while (data.next()) {
                    out.print("<div class='post'>");
                    out.print("<h5 class='date'>Date: ");
                    out.print(data.getString("post_date"));
                    out.print("</h5>");
                    out.print("<p class='post-text'> ");
                    out.print(data.getString("post_text"));
                    out.print("</p>");

                    out.print("<form class='likeform' action='/Like_post' method='post'>");
                    out.print("<button class='btn' name='like_id' value='");
                    out.print(data.getString("id"));
                    out.print("'>like</button>");
                    out.print("</form>");

//                    out.print("<p>");
//                    out.print(likes.getString("like_count"));
//                    out.print("</p>");

//                    out.print("<form class='dislikeform' action='/Dislike_post' method='post'>");
//                    out.print("<button class='btn' name='like_id' value='");
//                    out.print(likedata.getString("id"));
//                    out.print("'>unlike</button>");
//                    out.print("</form>");

                    out.print("<form class='deleteform' action='/Delete_post' method='post'>");
                    out.print("<button class='delete' name='post_id' value='");
                    out.print(data.getString("id"));
                    out.print("'>delete</button>");
                    out.print("</form>");

                    out.print("<form class='updatepost' action='/Update_post' method='post'>");
                    out.print("<textarea class=\"edit-post\" name=\"text\" cols=\"30\" rows=\"10\">");
                    out.print("</textarea>");
                    out.print("<button class='updatetext btn' name='post_id' value='");
                    out.print(data.getString("id"));
                    out.print("'>Update</button>");
                    out.print("</form>");

                    out.print("</div>");
                }
            }
            data.close();
        %>


    </div>
</div>
</body>
</html>
