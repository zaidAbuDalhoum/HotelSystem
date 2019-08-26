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
    <title>List Of Complaints</title>
</head>
<body>
<h1>Complaints List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>User Name</th><th>Room ID</th><th>Complaint</th><th>Date Of Complaint</th><th>Type Of Complaint</th><th>Reply to Complaint</th></tr>
    <c:forEach var="complaint" items="${list}">
        <tr>
            <td>${complaint.userName}</td>
            <td>${complaint.roomId}</td>
            <td>${complaint.message}</td>
            <td>${complaint.dateOfComplaint}</td>
            <td>${complaint.type}</td>
            <td>${complaint.reply}</td>
            <td>${complaint.dateOfReply}</td>

            <td><a href="complaintReplyEmployee/${complaint.complaintId}">Reply</a></td>


        </tr>
    </c:forEach>
</table>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>
<table align="center">
    <tr>
        <td> <a href="/complaintsEmployee">Back To List Of Complaints</a> </td>
    </tr>
</table>

<br/>
<table>
    <tr>
        <td></td>
        <td><a href="/employeeHomePage">Home</a>
        </td>
    </tr>
</table></body>
</html>
