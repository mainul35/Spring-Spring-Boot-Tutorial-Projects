<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Spring MVC Java Configuration - Form Submission</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/form-submit"
           method="POST" modelAttribute="user">
    Name: <form:input path="name" ></form:input><br><br>
    Email: <form:input path="email"></form:input><br><br>
    Age: <form:input path="age"></form:input><br><br>
    Sex: <form:radiobuttons path="sex" items="${sexTypes}"  /><br><br>
    Country:
    <form:select path="country">
        <form:options items="${countryList}" />
    </form:select><br><br>
    Phone Number: <form:input path="phoneNumber"></form:input><br><br>
    <input type="submit" name="Submit" value="Submit Data">
</form:form>
</body>
</html>