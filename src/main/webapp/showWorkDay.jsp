<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${not empty requestScope.isEmpty}">
    <p style="color: red">${requestScope.isEmpty}</p>
</c:if>
<c:if test="${not empty requestScope.deleted}">
    <p style="color: red">${requestScope.deleted}</p>
</c:if>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<c:forEach items="${requestScope.workDays}" var="day">
    <p>Date: ${day.day}</p>
    <form method="post" action="controller?action=delete_work_day">
        <input type="hidden" name="id" value="${day.id}">
        <input type="submit" value="Delete Work day"/>
    </form>
</c:forEach>
</body>
</html>
