<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/28/2020
  Time: 4:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand active" href="#">My Profile</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav navbar-light">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">Friends
                    <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/controller?command=user_find_user_friends&user_id=${sessionScope.user.id}">My Friends</a></li>
                        <li><a href="${pageContext.request.contextPath}/jsp/user/newFriends.jsp">New Friends</a></li>
                    </ul>
                        </li>
                <li><a href="#">Groups</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown">Notifications
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/controller?command=user_notification_friendship_requests&user_id=${sessionScope.user.id}">Friend Request Notification</a></li>
                        <li><a href="#">Groups Notification</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
