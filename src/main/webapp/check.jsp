<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Check is ok, click Update to update your profile</p>
<form method="post" action="controller?action=update">
    <input type="hidden" name="nickName" value="<%= request.getParameter("nickName") %>"/>
    <input type="hidden" name="lastName" value="<%= request.getParameter("lastName") %>"/>
    <input type="hidden" name="mail" value="<%= request.getParameter("mail") %>"/>
    <input type="hidden" name="phone" value="<%= request.getParameter("phone") %>"/>
    <input type="hidden" name="password" value="<%= request.getParameter("password") %>"/>
    <p><input type="submit" value="Update"/></p>
</form>
</body>
</html>
