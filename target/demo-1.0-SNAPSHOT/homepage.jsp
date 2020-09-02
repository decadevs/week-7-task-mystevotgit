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
</head>
<body>
<header>
    <div class="logo">facebook</div>
    <div class="container">
        <h1>Welcome</h1>
    </div>
</header>
<div class="contain">
    <div class="body">
        <br>
        <h2>Write a post</h2>
        <form action="Addpost" method="post">
            <textarea class="text-post" name="text" id="text" cols="30" rows="10"></textarea>
            <input type="submit" value="Publish Post" class="btn">
        </form>
        <br>
        <h2>Posts</h2>
        <%
            ResultSet data = (ResultSet) request.getAttribute("data");
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
                    out.print("<div class='actions'>");
                    out.print("<button class='edit'>edit</button>");
                    out.print("<button class='delete'>delete</button>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
            data.close();
        %>


    </div>
</div>
</body>
</html>
