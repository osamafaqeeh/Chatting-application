<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/16/2020
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <title>Hello, world!</title>
</head>
<body class="container">
    <style>
        .container {
            margin: 100px;
            background-color: #0f7864;
            color: #ffffff;
        }
        .form-group{
            padding: 8px;
        }
    </style>
    <form name="RegisterForm" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden"  name="command" value="common_register" />
        <div class="form-row">
            <div class="col-md-6 mb-3">
                <label for="validationDefault01">First name</label>
                <input type="text" class="form-control" id="validationDefault01" value="" required name="first_name">
            </div>
            <div class="col-md-6 mb-3">
                <label for="validationDefault02">Last name</label>
                <input type="text" class="form-control" id="validationDefault02" value="" required name="last_name" >
            </div>
        </div>
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail3" name="email">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword3" name="password">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPhoneNumber" class="col-sm-2 col-form-label">Phone Number</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPhoneNumber" name="phone_number">
            </div>
        </div>
        <button class="btn btn-primary " type="submit"  >Sign Up</button>
    </form>

</body>