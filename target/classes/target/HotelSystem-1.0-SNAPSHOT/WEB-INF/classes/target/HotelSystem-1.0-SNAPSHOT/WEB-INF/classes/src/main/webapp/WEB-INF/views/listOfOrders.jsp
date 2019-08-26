<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/23/19
  Time: 2:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/4/19
  Time: 3:40 AM
  To change this template use File | Settings | File Templates.

--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Of Orders</title>
</head>
<body>
<h1>Orders List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>User Name</th><th>Room ID</th><th>Bill Description</th><th>Bill Amount</th><th>Order Date</th><th>Order Status</th></tr>
    <c:forEach var="order" items="${list}">
        <tr>
            <td>${order.userName}</td>
            <td>${order.roomId}</td>
            <td>${order.bill}</td>
            <td>${order.billAmountWithTax}</td>
            <td>${order.orderDate}</td>
            <td>${order.orderStatus}</td>



        </tr>
    </c:forEach>
</table>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>
<br/>
<br/>
<table>
    <tr>
        <td></td>
        <td><a href="/adminHomePage">Home</a>
        </td>
    </tr>
</table></body>
</html>
