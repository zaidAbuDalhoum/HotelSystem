<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/20/19
  Time: 3:48 AM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Complaint Form</title>
</head>
<body>
<form:form id="registerComplaint" modelAttribute="complaint" action="complaintProcess" method="post">
    <table border="2" width="70%" cellpadding="2">
        <tr><th>Complaint</th><th>Type</th></tr>


            <td> <form:textarea path="message" id="message" rows="10" cols="60"/></td>


            <td> <form:select path="type" name="typeOfComplaint" id="typeOfComplaint">
                <form:option value="Restaurant">restaurant</form:option>
                <form:option value="Dry Cleaning">dryCleaning</form:option>
                <form:option value="Other">other</form:option>

            </form:select></td>

            </tr>

        <td><form:hidden path="userId" value = "${sessionScope.id}" /></td>

        <td><form:hidden path="userName" value = "${sessionScope.fullName}" /></td>


        <td><form:button id="complaint" name="complaint">Proceed</form:button></td>
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
