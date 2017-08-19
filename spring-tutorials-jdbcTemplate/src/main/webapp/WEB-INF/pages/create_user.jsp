<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
</head>
<body>
	<form:form method="POST" action="${pageContext.request.contextPath}/user/create" modelAttribute="user">
		<label>Name</label>
		<br>
		<form:input path="name" value="${user.name }"/><br>
		<label>Email</label><br>
		<form:input path="email" value="${user.email }"/><br>
		<label>Password</label><br>
		<form:password path="password" value="${user.password }"/><br>
		<label>Username</label><br>
		<form:input path="username" value="${user.username }"/><br>
		<label>Phone No:</label><br>
		<form:input path="phoneNo" value="${user.phoneNo }"/><br><br>
		<form:button type="submit">Submit Data</form:button>
	</form:form>
</body>
</html>