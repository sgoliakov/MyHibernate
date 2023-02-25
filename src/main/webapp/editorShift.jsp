<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Shifts</h2>
<c:choose>
    <c:when test="${not empty requestScope.shift}">
        <p style="color:blue;">${requestScope.shift}</p>
        <form method="post" action="controller?action=update_shift">
            <p>Start : <label>
                <input type="time" name="start" size="10" value="${requestScope.shift.start}"/>
            </label></p>
            <p>End : <label>
                <input type="time" name="end" size="10" value="${requestScope.shift.end}"/>
            </label></p>
            <input type="hidden" name="id" value="${requestScope.shift.id}">
            <p><input type="submit" value="Update"/></p>
        </form>
    </c:when>
    <c:otherwise>
        <p>Shift not found</p>
    </c:otherwise>
</c:choose>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
