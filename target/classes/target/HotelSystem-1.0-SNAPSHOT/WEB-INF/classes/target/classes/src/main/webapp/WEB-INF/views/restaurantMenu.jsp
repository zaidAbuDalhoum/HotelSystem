<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/17/19
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restaurant Menu</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Item</th><th>Price</th></tr>
    <c:forEach var="item" items="${list}">
        <tr>

            <td>${item.description}</td>
            <td>${item.price}</td>


        </tr>
    </c:forEach>
</table>
<table align="center">
    <tr>
<a href="/restaurantOrder">Place an Order</a>
    </tr>
</table>

<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
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
