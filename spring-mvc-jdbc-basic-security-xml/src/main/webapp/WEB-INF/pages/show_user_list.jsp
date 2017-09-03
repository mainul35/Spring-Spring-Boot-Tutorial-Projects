<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Password</th>
			<th>Username</th>
			<th>Phone No.</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.username}</td>
				<td>${user.phoneNo}</td>
				<td><a href="${pageContext.request.contextPath}/user/update/${user.id}">Edit</a></td>
				<td><a href="${pageContext.request.contextPath}/user/delete?id=${user.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>