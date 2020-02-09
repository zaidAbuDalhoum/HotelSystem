<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  com.hotelSys.model.User: zaid
  Date: 7/24/19
  Time: 4:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<form:form id="signUp form" modelAttribute="user" action="signUpProcess" method="post">

    <table align="center">

    <tr> <td> First Name <input id="firstName" type="text" name="firstName"></td> </tr>
        <tr> <td>Last Name <input id="lastName" type="text" name="lastName"></td></tr>
        <tr> <td>Date Of Birth (yyyy-mm-dd) <input id="dateOfBirth" type="text" name="dateOfBirth"></td></tr>
        <tr> <td>Phone Number <input id="phoneNumber" type="text" name="phoneNumber"></td></tr>
        <tr> <td>Password <input id="password" type="password" name="password"></td></tr>
        <tr> <td>Re-Enter Password <input id="password1" type="password" name="password1" ></td></tr>
        <tr> <td>Email <input id="email" type="text" name="email" onsubmit="return validateForm()"></td></tr>
        <tr> <td><label id="emailcheck"> </label></td></tr>

        <tr> <td> <input type="submit"> </td></tr>


</table>
</form:form>

<table align="center">
    <tr>
        <td></td>
        <td><a href="/welcomePage">Home</a>
        </td>
    </tr>
</table>


<script>function validateForm() {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    var x = document.getElementById("email").value;
    var j = new WebSocket("ws://localhost/ws");
    if (x.match(re) == false) {
        document.getElementsById("emailcheck").text = "invalid email";
        return false;
    }
}</script>
</body>
</html>
