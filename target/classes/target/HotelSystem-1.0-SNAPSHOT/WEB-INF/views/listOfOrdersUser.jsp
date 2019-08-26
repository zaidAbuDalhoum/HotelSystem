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
    <tr><th>User Name</th><th>Room ID</th><th>Bill Description</th><th>Bill Amount</th><th>Order Date</th><th>Order Type</th><th>Order Status</th><th>Delete Order</th></tr>
    <c:forEach var="order" items="${list}">
        <tr>orderDate
            <td>${order.userName}</td>
            <td>${order.roomId}</td>
            <td>${order.bill}</td>
            <td>${order.billAmountWithTax}</td>
            <td>${order.orderDate}</td>
            <td>${order.orderType}</td>
            <td>${order.orderStatus}</td>




            <td><a href="deleteOrder/${order.orderId}">Delete Order</a></td>


        </tr>
    </c:forEach>
</table>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>
<br/>
<a href="/listOfOrdersUser">Back To The List</a>
<br/>

<table>
    <tr>
        <td></td>
        <td><a href="/restaurantMenu">Restaurant Menu</a>
        </td>
    </tr>
</table>
<table>
    <tr>
        <td></td>
        <td><a href="/dryCleaningMenu">Dry Cleaning Menu</a>
        </td>
    </tr>
</table>

<table>
    <tr>
        <td></td>
        <td><a href="/Home">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
