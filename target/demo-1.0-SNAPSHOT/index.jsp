<%--
  Created by IntelliJ IDEA.
  User: decagon
  Date: 8/30/20
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Facebook - log in and sign up</title>
    <meta charset="utf-8">
    <link href='style.css' rel='stylesheet' type='text/css'>
</head>
<body>
<header>
    <div class="logo">facebook</div>
    <div class="container">
        <form action="Homepage" method="get" class="formulaire">
            <div class="userMail">
                <label for="mail">Email</label>
                <input type="text" name="mail" id="mail" />
            </div>
            <div class="password">
                <label for="password">Password</label>
                <input type="password" name="pword" id="password" />
            </div>
            <input type="submit" value="Login" class="btn" />
        </form>
        <div class="forgot"><a href="#">Forgot account?</a></div>
    </div>
</header>
<div class="left">
    <h2 class="connect">Connect with friends and the
        <br>
        world around you on Facebook.</h2>
    <ul class="center_list">
        <li class="list">
            <img class="img-list" src="https://fb-s-d-a.akamaihd.net/h-ak-xft1/v/t39.2365-6/851565_602269956474188_918638970_n.png?oh=894e994a5ae3ec3434da54fe42e31e72&oe=5971E6F5&__gda__=1500718098_effe0b8bd493ae0c23cc14fa99bda630">
            <big>See photos and updates</big> <small>from friends in News Feed.</small>
        </li>
        <li class="list">
            <img class="img-list" src="https://fb-s-c-a.akamaihd.net/h-ak-xaf1/v/t39.2365-6/851585_216271631855613_2121533625_n.png?oh=ae821220803265e80c0c261997b83a1b&oe=59295120&__gda__=1499782408_8fd3d0bad1815952ce6195f4e9c363df">
            <big>Share what's new</big> <small>in your life on your Timeline.</small>
        </li>
        <li class="list">
            <img class="img-list last" src="https://fb-s-b-a.akamaihd.net/h-ak-xft1/v/t39.2365-6/851558_160351450817973_1678868765_n.png?oh=8de3304d724a879592bcc3fe9f967dcd&oe=5959B4D8&__gda__=1499970870_5087f0141d0ed913a7092687c8c10213">
            <big>Find more</big> <small>of what you're looking for with Facebook Search.</small>
        </li>
    </ul>
</div>
<div class="right">
    <br>

    <h1 class="signUp">Sign Up</h1>
    <h4 class="signUpSub">It’s free and always will be.</h4>



    <form action="Homepage" method="post">
    <div class="inputs">
        <input type="text" class="input input_1" name="firstname" placeholder="First name">
        <input type="text" class="input input_1" name="lastname" placeholder="Last name">
        <br>
        <input type="text" class="input input_2" name="email" placeholder="Email">
        <br>
        <% String error = request.getAttribute("error") != null ? (String) request.getAttribute("error") : "";%>
        <p class="error"><%= error %></p>
        <br>
        <input type="password" class="input input_2" name="password" placeholder="New password">
    </div>
    <div>
        <h4>Birthday</h4>
        <select name="month" class="month">
            <option>Month</option>
            <option>01</option>
            <option>02</option>
            <option>03</option>
            <option>04</option>
            <option>05</option>
            <option>06</option>
            <option>07</option>
            <option>08</option>
            <option>09</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
        </select>
        <select name="day" class="day">
            <option>Day</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>13</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>
        </select>
        <select name="year" class="year">
            <option>Year</option>
            <option>2016</option>
            <option>2015</option>
            <option>2014</option>
            <option>2013</option>
            <option>2012</option>
            <option>2011</option>
            <option>2010</option>
            <option>2009</option>
            <option>2008</option>
            <option>2007</option>
            <option>2006</option>
            <option>2005</option>
            <option>2004</option>
            <option>2003</option>
            <option>2002</option>
            <option>2001</option>
            <option>2000</option>
            <option>1999</option>
            <option>1998</option>
            <option>1997</option>
            <option>1996</option>
            <option>1995</option>
            <option>1994</option>
            <option>1993</option>
            <option>1992</option>
            <option>1991</option>
            <option>1990</option>
        </select>
        <div class="why"><a style="text-decoration:none; color:#3c6ed6;" href="#">Why do I need to provide <br>my birthday?</a></div>
    </div>
    <div style="margin-top: 20px;">
        <input id="female" name="gender" type="radio" value="Female">
        <label class="female" for="female">Female</label>
        <input id="male" name="gender" type="radio" value="Male">
        <label class="male" for="male">Male</label>
    </div>
    <div class="terms">
        <p>By clicking Sign Up, you agree to our <a href="#" class="terms_a">Terms</a> and that you have read our <a href="#" class="terms_a">Data Policy</a>, including our <a href="#" class="terms_a">Cookie Use</a>.</p>
    </div>
    <div>
        <input type="submit" class="signUpBtn" name="submit">
    </div>
    </form>
    <div class="formbox">
        <div class="create"><a href="">Create a Page</a> for a celebrity, band or business.</div>
    </div>
</div>
</body>
</html>
