<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Schedule</title>
</head>
<body>
<h2>My Schedule</h2>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<form method="post" action="controller?action=free_schedule_shifts">
    <input type="submit" value="Add shift into Schedule"/>
</form>
<с:forEach items="${sessionScope.mySchedule}" var="shift">
    <p>${shift.fk.workDay} : ${shift.shift}</p>
</с:forEach>
<с:if test="${not empty requestScope.notExists}">
    <p>This schedule not exists</p>
</с:if>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<form method="post" action="controller?action=free_schedule_shifts">
    <input type="submit" value="Add shift into Schedule"/>
</form>
</body>
</html>
