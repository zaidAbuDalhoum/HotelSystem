<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: zaid
  Date: 8/4/19
  Time: 4:10 AM
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<%--TODO: Unify head tags in one JSP--%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reservation Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>jQuery UI Datepicker - Default functionality</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#checkIn" ).datepicker();
        } );

        $( function() {
            $( "#checkOut" ).datepicker();
        } );

    </script>

</head>
<body>

<td><h3>Welcome ${fullName}</h3></td>

<form:form id="reservationForm" modelAttribute="reservationWrapper" action="searching" method="post">
    <table align="center">
        <tr>

            <td>

                <form:label path="checkIn">Checkin Date : </form:label>
            </td>

            <td>
                <form:input  path="checkIn"  name="checkIn" id="checkIn" />
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="checkOut">Checkout Date : </form:label>
            </td>
            <td>
                <form:input   path="checkOut" name="checkOut" id="checkOut" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="roomSize">Room Size:</form:label>
            </td>
            <td>
                <form:select path="roomSize" name="roomSize" id="roomSize">
                    <form:option value="single">Single</form:option>
                    <form:option value="double">Double</form:option>
                    <form:option value="suite">Suite</form:option>
                </form:select>

            </td>
        </tr>
        <tr>
            <td><form:hidden path="userName" id = "userName" value = "${sessionScope.fullName}" /></td>
            <td><form:hidden path="roomId" id="roomId" value = "${room.roomId}" /></td>
            <td></td>
            <td align="center">
                <form:button id="reservationWrapper" name="reservationWrapper">Search</form:button>
            </td>
        </tr>
        <tr></tr>

    </table>
</form:form>
<table align="center">
    <tr>
        <td style="font-style: italic; color: red;">${message}</td>
    </tr>
</table>
<table align="center">
    <tr>
        <td></td>
        <td><a href="/Home">Home</a>
        </td>
    </tr>
</table>
</body>
</html>
