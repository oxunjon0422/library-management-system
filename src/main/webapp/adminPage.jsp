<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 31/07/2022
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .hero-image {
            background-image: url("https://divisare-res.cloudinary.com/images/c_limit,f_auto,h_2000,q_auto,w_3000/v1569222525/tpaduyzk8ekb7dlwez4a/snohetta-michael-grimm-charles-library.jpg");
            background-color: #cccccc;
            height: 600px;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            position: relative;
        }

        .hero-text {
            text-align: center;
            position: absolute;
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
            color: white;
        }
    </style>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="hero-image">
    <div class="hero-text">
        <h1 style="font-size:50px"></h1>
        <h3></h3>
        <div align="center">
            <button><a href="/add-book">Add Book</a><br><br></button>
<%--            <button><a href="signup.jsp">Add User</a><br><br></button>--%>
<%--            <button><a href="showUser.jsp">Show users</a><br><br></button>--%>
<%--            <button><a href="showBook">Show Book</a><br><br></button>--%>
            <button><a href="books">Home page</a><br><br></button>
            <button><a href="update2">Update</a><br><br></button>
            <button><a href="deleteBook">Delete book</a><br><br></button>



        </div>
    </div>
</div>
</body>
</html>


</body>
</html>