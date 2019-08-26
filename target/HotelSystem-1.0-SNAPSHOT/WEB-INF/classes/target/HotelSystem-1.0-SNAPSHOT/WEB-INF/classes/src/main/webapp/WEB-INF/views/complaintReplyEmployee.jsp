<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/23/19
  Time: 1:59 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Reply Form</title>
</head>
<body>

<td>Welcome ${fullName}</td>

<%--@elvariable id="reservation" type="com"--%>
<form:form id="replyToComplaint" modelAttribute="complaintReply" action="registerReplyEmployee" method="post">
    <table border="2" width="70%" cellpadding="2">
        <tr><th>User Name</th><th>Room Id</th><th>Complaint</th><th>Date Of Complaint</th><th>Register Reply </th></tr>
        <tr>
            <td>${complaint.userName}</td>
            <td>${complaint.roomId}</td>
            <td>${complaint.message}</td>
            <td>${complaint.dateOfComplaint}</td>
            <td> <form:textarea path="reply" id="message" rows="10" cols="60"/></td>
            <td><form:hidden path="complaintId" value = "${complaint.complaintId}" /></td>

            <td>
            </td>

            <td align="left">


                <form:button id="replyToComplaint" name="replyToComplaint" >Register Reply</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="Home">Home</a>
            </td>
        </tr>
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
        <td><a href="/employeeHomePage">Home</a>
        </td>
    </tr>
</table>
</body>
</html>

