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
    <title>List Of Items</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Item</th><th>Price</th><th>Delete Item</th></tr>
    <c:forEach var="item" items="${list}">
        <tr>

            <td>${item.description}</td>
            <td>${item.price}</td>
            <td><a href="/deleteItem/${item.id}">Delete This Item</a></td>

        </tr>
    </c:forEach>
</table>
<table align="center">
    <tr>
        <a href="/addItem">Add a new Item</a>
    </tr>
</table>

<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
    <tr>
        <td><a href="/viewItems">Back To Items List</a> </td>
    </tr>
</table>
<table>
    <tr>
        <td></td>
        <td><a href="/employeeHomePage">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
