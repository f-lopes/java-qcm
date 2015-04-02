<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 3/11/2015
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Profile</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">

        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>

        <h2><spring:message code="student.profile"/> ${currentUser.firstName}!</h2>
        <br/>
        <h4><spring:message code="student.details"/></h4>
        <ul>
            <li><spring:message code="student.firstname"/> ${currentUser.firstName}</li>
            <li><spring:message code="student.lastname"/> ${currentUser.lastName}</li>
            <li><spring:message code="student.email"/> ${currentUser.email}</li>
            <li><spring:message code="evaluation.selected.grade"/> ${currentUser.grade.name}</li>
        </ul>
    </div>
</body>
</html>
