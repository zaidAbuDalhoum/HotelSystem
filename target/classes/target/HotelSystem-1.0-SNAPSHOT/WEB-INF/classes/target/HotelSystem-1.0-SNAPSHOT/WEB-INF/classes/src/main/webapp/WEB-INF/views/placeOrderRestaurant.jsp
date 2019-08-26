<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/17/19
  Time: 1:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<form:form id="makeRestaurantOrder" modelAttribute="orderWrapper" action="restaurantOrderProcess" method="post">
    <table border="2" width="70%" cellpadding="2">
        <tr><th>Choose Here</th><th>Item</th><th>Price</th><th>Quantity</th></tr>
        <c:forEach var="item" items="${list}">
           <td> <form:checkbox path="items" value= "${item.id}"/></td>

            <td>${item.description}</td>
                <td>${item.price}</td>
            <td> <form:select path="quantity" name="itemQuantity" id="itemQuantity">
                <form:option value="1">One</form:option>
                <form:option value="2">Two</form:option>
                <form:option value="3">Three</form:option>
                <form:option value="4">Four</form:option>
                <form:option value="5">Five</form:option>
                <form:option value="6">Six</form:option>

            </form:select></td>

            </tr>
        </c:forEach>

        <td><form:hidden path="userId" value = "${sessionScope.id}" /></td>

        <td><form:hidden path="userName" value = "${sessionScope.fullName}" /></td>


        <td><form:button id="orderWrapper" name="orderWrapper">Proceed</form:button></td>
    </table>

</form:form>
<table>
    <tr>
        <td></td>
        <td><a href="/Home">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
