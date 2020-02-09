
%--
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
<h1>List Of Upcoming Reservations</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Reservation Id</th><th>User Name</th><th>Room Id</th><th>CheckIn Date</th><th>CheckOut Date</th><th>Room Size</th></tr>
    <c:forEach var="reservation" items="${list}">
        <tr>
            <td>${reservation.reservationId}</td>
            <td>${reservation.userName}</td>
            <td>${reservation.roomId}</td>
            <td>${reservation.checkIn}</td>
            <td>${reservation.checkOut}</td>
            <td>${reservation.roomSize}</td>


        </tr>
    </c:forEach>
</table>
<br/>
<table>
    <tr>
        <td></td>
        <td><a href="/adminHomePage">Home</a>
        </td>
    </tr>
</table></body>
</html>
