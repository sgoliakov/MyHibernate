<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Report</title>
</head>
<body>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<c:if test="${not empty requestScope.dayReport}">
    <h2>Day report : ${requestScope.dayReport} </h2>
</c:if>
<c:if test="${not empty requestScope.amount}">
    <p>All amount employee : ${requestScope.amount} </p>
</c:if>
<c:if test="${not empty requestScope.schedules}">
    <p>Занятые смены </p>
    <table cellpadding="1" cellspacing="1" border="1">
        <tr>
            <td>Name</td>
            <td>mail</td>
            <td>phone</td>
            <td>Work day</td>
            <td>Work shift</td>
        </tr>
        <c:forEach items="${requestScope.schedules}" var="free">
            <tr>
                <td>${free.fk.employee.nickName}</td>
                <td>${free.fk.employee.mail}</td>
                <td>${free.fk.employee.phone}</td>
                <td>${free.fk.workDay.day}</td>
                <td>${free.shift.start} - ${free.shift.end}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br/>
<c:if test="${not empty requestScope.freeSchedules}">
    <p>Осталось свободных смен до - ${requestScope.lastDay} включительно</p>
    <table cellpadding="1" cellspacing="1" border="1">
        <tr>
            <td>Work day</td>
            <td>Number shift</td>
            <td>Work shift</td>
        </tr>
        <c:forEach items="${requestScope.freeSchedules}" var="free">
            <tr>
                <td>${free.day.day}</td>
                <td style="text-align: center">${free.shift.id}</td>
                <td>${free.shift.start} - ${free.shift.end}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
