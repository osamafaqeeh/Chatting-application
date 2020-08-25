<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/20/2020
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<p>requestURI  : ${pageContext.errorData.requestURI}</p>
<p>servletName  : ${pageContext.errorData.servletName}</p>

<p> statusCode  :${pageContext.errorData.statusCode}</p>

<p>throwable :${pageContext.errorData.throwable}</p>

</body>
</html>
