<%--
  Created by IntelliJ IDEA.
  User: Даша
  Date: 29.09.2020
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="shortcut icon" href="images/shelf.png" type="image/x-icon">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="styles/error.css">
</head>
<body>
<h1>Error!</h1>
<h2>${exception.getMessage()}</h2>
</body>
</html>
