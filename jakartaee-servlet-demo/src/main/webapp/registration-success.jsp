<%--
  Created by Syed Mainul Hasan
  User: Mainul35
  Date: 2/7/2023
  Time: 1:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration Success</title>
</head>
<body>
<p>First name: <c:out value="${user.firstName}"></c:out></p>
<p>Last name: <c:out value="${user.lastName}"></c:out></p>
</body>
</html>
