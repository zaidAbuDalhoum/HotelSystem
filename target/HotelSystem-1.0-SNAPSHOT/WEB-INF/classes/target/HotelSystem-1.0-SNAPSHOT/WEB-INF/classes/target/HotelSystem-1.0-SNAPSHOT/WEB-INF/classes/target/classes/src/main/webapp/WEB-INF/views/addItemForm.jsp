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
<form:form id="addItemForm" modelAttribute="item" action="addItemProcess" method="post">

    <table align="center">

        <tr> <td> Item Description <input id="description" type="text" name="description"></td> </tr>
        <tr> <td>Price <input id="price" type="text" name="price"></td></tr>
       <tr><td><form:hidden path="type" value = "${sessionScope.permission}"></form:hidden></td></tr>

        <tr> <td> <input type="submit"> </td></tr>


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
