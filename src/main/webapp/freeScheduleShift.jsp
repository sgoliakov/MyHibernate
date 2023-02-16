<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Free shift</title>
</head>
<body>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<c:choose>
    <c:when test="${sessionScope.employee.admin == false}">
        <c:forEach items="${freeSchedule}" var="shift">
            <form method="post" action="controller?action=add_shift_into_my_schedule">
                <input type="hidden" name="id" value="${shift.id}"/>
                <p>${shift.day} : ${shift.shift} - <input type="submit" value="Add"/></p>
            </form>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty requestScope.Remove}">
            <p>"${requestScope.Remove}"</p>
        </c:if>
        <c:forEach items="${freeSchedule}" var="shift">
            <form method="post" action="controller?action=remove_shift">
                <p>${shift.day} : ${shift.shift}</p>
                <input type="hidden" name="id" value="${shift.id}"/>
                <input type="submit" value="Delete"/></p>
            </form>
        </c:forEach>
    </c:otherwise>
</c:choose>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
