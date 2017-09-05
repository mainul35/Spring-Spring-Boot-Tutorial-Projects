<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="css.jsp" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log in</title>
</head>
<jsp:include page="header.jsp" />
<body>
	<div class="wrapper">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<form id="login" action="${pageContext.request.contextPath}/login-process"
						method='POST'>
						<fieldset>
							<div class='login-msg'>
								<c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								<c:if test="${not empty msg}">
									<div class="msg">${msg}</div>
								</c:if>
								<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
									<font color="red"> <c:out
											value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />.
									</font>
								</c:if>
							</div>
						</fieldset>
						<fieldset>
							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									class="form-control" id="username" value="" placeholder="Enter username"
									name="username"/>
							</div>
							<div class="form-group">
								<label for="password">Password</label> <input type="password"
									class="form-control" id="password" placeholder="Password"
									name="password"/>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> 
								<input type="submit" value="Log in"
								class="btn btn-primary">
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<jsp:include page="js.jsp" />