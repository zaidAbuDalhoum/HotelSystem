<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/22/19
  Time: 6:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Room</title>
</head>
<body>
<form:form id="addRoomForm" modelAttribute="room" action="addRoomProcess" method="post">

    <table align="center">

        <tr> <td> Room Id <input id="roomId" type="text" name="roomId"></td> </tr>
        <tr> <td>Price Per Night <input id="pricePerNight" type="text" name="pricePerNight"></td></tr>
       <tr><td> Room Size <form:select path="roomSize" name="roomSize" id="roomSize">
            <form:option value="single">Single</form:option>
            <form:option value="double">Double</form:option>
            <form:option value="suite">Suite</form:option>
       </form:select></td></tr>
        <tr> <td>Room Description <input id="description" type="text" name="description"></td></tr>


        <tr> <td> <input type="submit"> </td></tr>


    </table>
</form:form>


<table>
    <tr>
        <td></td>
        <td><a href="/adminHomePage">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
