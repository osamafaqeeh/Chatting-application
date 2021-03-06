<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/2/2020
  Time: 12:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Title</title>
    <style>
        .container {
            padding: 2rem 0rem;
        }

        h4 {
            margin: 2rem 0rem 1rem;
        }

        .table-image {
        td, th {
            vertical-align: middle;
        }
        }
    </style>
</head>
<body>
<%@include file="/jsp/common/header.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-8">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th scope="col">#NUM</th>
                    <th scope="col">Name</th>
                    <th scope="col">Status</th>
                    <th scope="col">Date</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.list}" var="notifications" varStatus="loopCounter">
                    <tr>
                        <th scope="row">${loopCounter.count}</th>
                            <td>${sessionScope.usersNameAndId[notifications.notificationSenderId]}</td>
                            <td>${notifications.notificationStatus}</td>
                            <td>${notifications.notificationDate}</td>
                        <td>
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/controller?command=user_create_friendship&sender_id=${notifications.notificationSenderId}&receiver_id=${sessionScope.user.id}&notification_id=${notifications.id}"role=button>Accept</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
