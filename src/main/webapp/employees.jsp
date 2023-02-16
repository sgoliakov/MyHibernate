<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
<c:if test="${not empty requestScope.delete}">
    <p>"${requestScope.delete}"</p>
</c:if>
<c:choose>
    <c:when test="${not empty employees}">
        <table cellpadding="1" cellspacing="1" border="1">
            <tr>
                <td>Name</td>
                <td>Last name</td>
                <td>mail</td>
                <td>phone</td>
                <td>delete</td>
            </tr>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <c:choose>
                        <c:when test="${employee.admin == false}">
                            <td>${employee.firstName}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.mail}</td>
                            <td>${employee.phone}</td>
                            <td>
                                <form method="post" action="controller?action=delete_employee">
                                    <input type="hidden" name="id" value="${employee.id}"/>
                                    <p><input type="submit" value="Delete employee"/></p>
                                </form>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="4" align="center">Admin</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:if test="${not empty requestScope.notFound}">
            <p>"${requestScope.notFound}"</p>
        </c:if>
    </c:otherwise>
</c:choose>
<form method="post" action="controller?action=main">
    <input type="submit" value="Go to main"/>
</form>
</body>
</html>
