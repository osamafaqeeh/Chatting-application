<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/21/2020
  Time: 7:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<c:if test="${sessionScope.user !=null }">
<head>
    <!-- Theme Made By www.w3schools.com - No Copyright -->
    <title>Bootstrap Theme Simply Me</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        .bg-1 {
            background-color: #1abc9c;
            color: #ffffff;
        }
        .bg-2 {
            background-color: #0f7864;
            color: #ffffff;
        }
        .bg-3 {
            background-color: #ffffff;
            color: #555555;
        }
        .container-fluid {
            padding-top: 70px;
            padding-bottom: 70px;
        }
        .navbar {
            padding-top: 15px;
            padding-bottom: 15px;
            border: 0;
            border-radius: 0;
            margin-bottom: 0;
            font-size: 15px;
            letter-spacing: 3px;
        }
        .navbar-nav  li a:hover {
            color: #0f7864 !important;
        }
    </style>
</head>
<body>
<c:choose>
<c:when test="${sessionScope.user.userRole == 'USER'}">
<%@include file="header.jsp"%>
<div class="container-fluid bg-2 text-center">
    <h2>Welcome</h2><br>
    <h3>${sessionScope.user.firstName} ${sessionScope.user.lastName}</h3
    <h3>${sessionScope.user.email}</h3>
</div>
</c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
</body>
</c:if>
</html>
