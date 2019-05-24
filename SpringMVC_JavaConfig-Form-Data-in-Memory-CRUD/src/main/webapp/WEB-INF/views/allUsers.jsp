<%--
  Created by IntelliJ IDEA.
  User: Mainul
  Date: 12/27/2017
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<h1>Showing the list of all users</h1>
</body>
<table>
<tr>
    <td>ID</td><td>Name</td><td>Email</td><td>Age</td><td>Sex</td><td>Contact</td><td>Nationality</td><td>Update</td><td>Delete</td>
</tr>
<c:forEach items="${users}" var="user">
<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
    <td>${user.age}</td>
    <td>${user.sex}</td>
    <td>${user.phoneNumber}</td>
    <td>${user.country}</td>
    <td><a href="${pageContext.request.contextPath}/update?email=${user.email}">Update</a></td>
    <td><a href="${pageContext.request.contextPath}/delete?email=${user.email}">Delete</a></td>
</tr>
</c:forEach>
</table>
</html>
