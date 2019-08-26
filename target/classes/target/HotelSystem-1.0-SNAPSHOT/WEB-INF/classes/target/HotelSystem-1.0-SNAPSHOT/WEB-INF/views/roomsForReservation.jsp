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
    <title>List Of Available Rooms</title>
</head>
<body>
<h1>Available Rooms</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Room Id</th><th>Size</th><th>Price Per Night</th><th>Description</th><th>Book</th></tr>
    <c:forEach var="room" items="${list}">
                <tr>
            <td>${room.roomId}</td>
            <td>${room.roomSize}</td>
            <td>${room.pricePerNight}</td>
            <td>${room.description}</td>
                <td><a href="roomInformation/${room.id}">Book</a></td>


        </tr>
    </c:forEach>
</table>
<br/>
<table>
    <tr>
        <td></td>
        <td><a href="/Home">Home</a>
        </td>
    </tr>
</table></body>
</html>
