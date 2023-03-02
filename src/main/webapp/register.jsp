<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<c:if test="${not empty requestScope.notAdd}">
    <p style="color:red;">${requestScope.notAdd}</p>
</c:if>
<form method="post" action="controller?action=register">
    <p>nickName <label>
        <input type="text" name="nickName" size="10" value="min 3 symbol"/>
    </label></p>
    <p>lastName <label>
        <input type="text" name="lastName" size="10" value="min 3 symbol"/>
    </label></p>
    <p>mail <label>
        <input type="text" name="mail" size="10" value="must be valid"/>
    </label></p>
    <p>phone <label>
        <input type="text" name="phone" size="10" value="+38"/>
    </label></p>
    <p>password <label>
        <input type="password" name="password" size="10"/>
    </label></p>
    <p>
        <label>
            <select size="3" name="admin">
                <option value="true">Admin</option>
                <option value="false">User</option>
            </select>
        </label>
    </p>
    <p><input type="submit" value="Register"/></p>
</form>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
