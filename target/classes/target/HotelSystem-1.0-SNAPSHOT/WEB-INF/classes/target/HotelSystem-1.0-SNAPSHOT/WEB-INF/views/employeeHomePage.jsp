<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 7/25/19
  Time: 4:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Welcome</title>
</head>
<body>
<table align="center">
    <tr>
        <td><h3>Welcome ${fullName}</h3></td><br>

        <td>${message}</td>
    </tr>
    <tr>
    </tr>
    <tr>
    </tr>

    <br>
    <tr>
        <td><a href="/viewItems">View the Items List</a></td>
    </tr>

    <br>
    <tr>
        <td><a href="/pendingOrdersEmployee">View Pending Orders</a></td>
    </tr>
    <br>
    <br>
    <tr>
        <td><a href="/complaintsEmployee">View Complaints</a></td>
    </tr>
    <br>
    <tr>
        <td><a href="/logOut">Log Out</a></td>
    </tr>
</table>
</body>
</html>