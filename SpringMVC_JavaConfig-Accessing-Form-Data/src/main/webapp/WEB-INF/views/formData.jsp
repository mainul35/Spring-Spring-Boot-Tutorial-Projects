<%--
  Created by IntelliJ IDEA.
  User: Mainul
  Date: 12/26/2017
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spring MVC Java Configuration - Rendering form data</title>
</head>
<body>
<h1>Rendering data based on form submission</h1><br>
<h3>ID: ${user.id}</h3>
Name: ${user.name}<br><br>
Email: ${user.email}<br><br>
Age: ${user.age}<br><br>
Sex: ${user.sex}<br><br>
Phone Number: ${user.phoneNumber}<br><br>
Country: ${user.country}<br><br>
<a href="${pageContext.request.contextPath}/user-form">&lt; Go Back</a>
</body>
</html>
