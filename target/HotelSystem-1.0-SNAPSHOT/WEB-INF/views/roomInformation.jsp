<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/4/19
  Time: 4:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reservation Form</title>
</head>
<body>

<td>Welcome ${fullName}</td>

<%--@elvariable id="reservation" type="com"--%>
<form:form id="reservationInformation" modelAttribute="reservation" action="reservationProcess" method="post">
    <table border="2" width="70%" cellpadding="2">
        <tr><th>Room Id</th><th>Price Per Night</th><th>Description</th><th>Room Size</th><th>Confirm Reservation</th></tr>
        <tr>
            <td>${room.roomId}</td>
            <td>${room.pricePerNight}</td>
            <td>${room.description}</td>
            <td>${room.roomSize}</td>
            <td>
            </td>

            <td align="left">





           <td><form:button id="reservation" name="reservation" >Confirm</form:button></td>
        </tr>
        <tr></tr>
    </table>
</form:form>
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
