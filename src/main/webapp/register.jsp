<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<c:if test="${not empty requestScope.notAdd}">
    <p>This employee is exists</p>
</c:if>
<form method="post" action="controller?action=register">
    <p>firstName <input type="text" name="firstName" size="10"/></p>
    <p>lastName <input type="text" name="lastName" size="10"/></p>
    <p>mail <input type="text" name="mail" size="10"/></p>
    <p>phone <input type="text" name="phone" size="10"/></p>
    <p>password <input type="password" name="password" size="10"/></p>
    <p>
        <select size="3" name="admin">
            <option value="true">Admin</option>
            <option value="false">User</option>
        </select>
    </p>
    <p><input type="submit" value="Register"/></p>
</form>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
