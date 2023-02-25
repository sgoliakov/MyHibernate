<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Shifts</title>
</head>
<body>
<h2>Shifts</h2>
<c:if test="${not empty requestScope.updated}">
    <p style="color:blue;">${requestScope.updated}</p>
</c:if>
<c:forEach items="${requestScope.listShift}" var="shift">
    <p>${shift.start} : ${shift.end}</p>
    <form method="post" action="controller?action=editor_shift">
        <input type="hidden" name="id" value="${shift.id}"/>
        <input type="submit" value="Update"/>
    </form>
</c:forEach>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
