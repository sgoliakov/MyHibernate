<jsp:useBean id="employee" scope="session" type="MyProject.entity.Employee"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Editor</title>
</head>
<body>
<c:if test="${not empty sessionScope.updated}">
    <p style="color:blue;">${sessionScope.updated}</p>
</c:if>
<form method="post" action="controller?action=update">
    <p>nickName <label>
        <input type="text" name="nickName" size="10" value="${employee.nickName}"/>
    </label></p>
    <p>lastName <label>
        <input type="text" name="lastName" size="10" value="${employee.lastName}"/>
    </label></p>
    <p>mail <label>
        <input type="text" name="mail" size="10" value="${employee.mail}"/>
    </label></p>
    <p>phone <label>
        <input type="text" name="phone" size="10" value="${employee.phone}"/>
    </label></p>
    <p>password <label>
        <input type="password" name="password" size="10" value="${employee.password}"/>
    </label></p>
    <p><input type="submit" value="Update"/></p>
</form>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
