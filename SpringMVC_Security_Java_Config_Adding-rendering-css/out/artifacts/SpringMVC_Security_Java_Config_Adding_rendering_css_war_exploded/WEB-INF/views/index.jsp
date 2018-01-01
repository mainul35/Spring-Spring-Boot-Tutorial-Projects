<%--<jsp:directive.page contentType="text/html;charset=UTF-8" />--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mainul
  Date: 12/28/2017
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel='stylesheet' type="text/css"
          href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
    <link rel='stylesheet' href="${pageContext.request.contextPath}/resources/style.css" type="text/css">

    <title>Spring MVC Java Config - Including bootstrap</title>
</head>
<body>
<div class="container">
    <h1>Spring MVC Java Config - With in memory data CRUD</h1>
    <div class="container"><br/>
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Success!</strong> It is working as we expected.
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/admin/user-form">Please click here to add user.</a>

</div>
</body>
</html>
