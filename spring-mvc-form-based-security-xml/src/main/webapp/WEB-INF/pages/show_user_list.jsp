<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="css.jsp" />

<nav class="navbar navbar-toggleable-md navbar-inverse bg-primary">
	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarColor01"
		aria-controls="navbarColor01" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="#">Navbar</a>

	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home</a>
			</li>
		</ul>
		<a href="" class="text-right">${userName}</a>&nbsp;&nbsp;&nbsp;
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-success">Log Out</a>
		</sec:authorize>
	</div>
</nav>
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
				<td><a
					href="${pageContext.request.contextPath}/user/update/${user.id}">Edit</a></td>
				<td><a
					href="${pageContext.request.contextPath}/user/delete?id=${user.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
<jsp:include page="js.jsp" />