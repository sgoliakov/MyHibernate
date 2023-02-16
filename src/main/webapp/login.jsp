<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<с:if test="${not empty requestScope.notExists}">
    <p>This user not exists</p>
</с:if>
<form method="post" action="controller?action=login">
    <p>Name:<input type="text" name="firstName" size="10"/></p>
    <p>Password:<input type="password" name="password" size="10"/></p>
    <p><input type="submit" value="Login"/></p>
</form>
<form method="post" action="register.jsp">
    <input type="submit" value="Register"/>
</form>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
