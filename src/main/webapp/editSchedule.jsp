<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Schedule</title>
</head>
<body>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<c:if test="${not empty requestScope.isEmpty}">
    <p style="color: red">${requestScope.isEmpty}</p>
</c:if>
<c:if test="${not empty requestScope.deleted}">
    <p style="color: blue">${requestScope.deleted}</p>
</c:if>
<h3 style="color: darkgreen">Schedule : ${requestScope.employee.nickName}</h3>
<c:forEach items="${requestScope.listWrapperByID}" var="wrapper">
<form method="post" action="controller?action=delete_schedule_employee">
    <input type="hidden" name="day" value="${wrapper.day.id}"/>
    <input type="hidden" name="id" value="${requestScope.employee.id}"/>
    <p>${wrapper.day} : ${wrapper.shift} - <input type="submit" value="Delete"/></p>
    </form>
</c:forEach>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
