<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.employee}">
        <p>Hello :${sessionScope.employee.firstName}</p>
        <p><a href="controller?action=schedule_by_id">My schedule</a></p>
        <p><a href="controller?action=free_schedule_shifts">Add shift into Schedule</a></p>
        <p><a href="controller?action=free_schedule_shifts">Show free Shift</a></p>
        <p><a href="controller?action=logout">Logout</a></p>
    </c:when>
    <c:otherwise>
        <p><a href="register.jsp">Register</a></p>
        <p><a href="login.jsp">Login</a></p>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${empty sessionScope.employee}">
        <c:forEach items="${requestScope.workDays}" var="day">
            <br/>
            <p>Date: ${day.day}</p>
            <form method="post" action="register.jsp">
                <input type="submit" value="Register for add this day to the schedule"/>
            </form>
            <form method="post" action="login.jsp">
                <input type="submit" value="Login for add this day to the schedule"/>
            </form>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${sessionScope.employee.admin == false}">
                <c:forEach items="${freeSchedules}" var="shift">
                    <form method="post" action="controller?action=add_shift_into_my_schedule">
                        <input type="hidden" name="id" value="${shift.id}"/>
                        <p> ${shift} <input type="submit" value="Add shift to my schedule"/></p>
                    </form>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p><a href="controller?action=show_plan">Add new shift</a></p>
                <p><a href="controller?action=free_schedule_shifts">Remove shift</a></p>
                <p><a href="controller?action=show_employees">Show employees</a></p>
                <p><a href="controller?action=create_month_plan">Create new Month Plan</a></p>
                <p><a href="controller?action=create_report">Create report</a></p>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
</body>
</html>
