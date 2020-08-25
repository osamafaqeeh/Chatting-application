<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/29/2020
  Time: 12:43 PM
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
        <form class="form-inline text-center" action="${pageContext.request.contextPath}/controller" method="post">
            <div>
            <input type="hidden" name="command" value="user_new_friends" >
                <label>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
                        <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
                    </svg>
                </label>
            <input name="full_name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" size="50">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </div>
        </form>
<c:if test="${requestScope.list != null}">

    <div class="container">
        <div class="row">
            <div class="col-12">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">#NUM</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.list}" var="user" varStatus="loopCounter">
                    <tr>
                        <th scope="row">${loopCounter.count}</th>
                        <td>${user.firstName} ${user.lastName} </td>
                        <td>${user.email}</td>
                        <td>${user.phoneNumber}</td>
                        <td>
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/controller?command=user_send_notification&notification_type=FRIEND_SHIP&sender_id=${sessionScope.user.id}&receiver_id=${user.id}&notification_type=FRIEND_SHIP"role=button>Add Friend</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
