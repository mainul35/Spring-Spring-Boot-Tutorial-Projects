<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <%--<link rel='stylesheet' type="text/css"--%>
          <%--href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">--%>
        <link rel='stylesheet' type="text/css" href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
        <link rel='stylesheet' href="${pageContext.request.contextPath}/resources/style.css" type="text/css">
        <title>Spring MVC Java Configuration - Form Submission</title>
</head>
<body>

<br><br>
<div class="container">
    <h1>Please enter your information</h1>
    <form:form action="${pageContext.request.contextPath}/admin/form-submit"
               method="POST" modelAttribute="user">
        <input type="hidden" value="${actionType}" name="actionType">
        <form:input path="id" type="hidden" value="${id}"></form:input>
        <div class="form-group row">
            <label for="name" class="col-sm-2 col-form-label">Name:</label>
            <form:input path="name" value="${user.name}" cssClass="form-control" id="name"></form:input>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <form:input path="email" value="${user.email}" cssClass="form-control"></form:input>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Age: </label>
            <form:input path="age" value="${user.age}" cssClass="form-control"></form:input><br><br>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Sex: </label>
            <form:radiobuttons path="sex" items="${sexTypes}" cssClass="form-check-radio"/><br><br>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Country: </label>
            <form:select path="country">
                <form:option selected="true" value="${empty user.country?'Please Select a Country':user.country}"/>
                <form:options items="${countryList}"/>
            </form:select>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Phone Number: </label>
            <form:input path="phoneNumber" value="${user.phoneNumber}" cssClass="form-control"></form:input>
        </div>

        <input type="submit" name="Submit" value="Submit Data" class="form-control">
    </form:form>
</div>

</body>
</html>