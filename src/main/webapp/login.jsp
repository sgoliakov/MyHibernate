<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<с:if test="${not empty requestScope.notUser}">
    <p style="color:red;">${requestScope.notUser}</p>
</с:if>
<form method="post" action="controller?action=login">
    <p>Name:<label>
        <input type="text" name="nickName" size="10"/>
    </label></p>
    <p>Password:<label>
        <input type="password" name="password" size="10"/>
    </label></p>
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
