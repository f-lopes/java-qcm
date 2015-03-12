<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html ng-app="qcmUsersApp">
<head>
    <title><spring:message code="students.list"/></title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body >
<div class="container">

    <sec:authorize access="isAuthenticated()" >
        <%@include file="../menu/menuByRole.jsp"%>
    </sec:authorize>
    
    <h1><spring:message code="students.list"/></h1>

    <c:forEach items="${grades}" var="grade">
        <a href=" <c:url value="/student/all?grade=${grade.name}"/>">${grade.name}</a> <br/>
    </c:forEach>

    <c:if test="${grade != null}">
        ${grade}
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <td>Name</td>
            <td>First name</td>
            <td>Email</td>
            <td>Grade</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.lastName}</td>
                <td>${student.firstName}</td>
                <td>${student.email}</td>
                <td>${student.grade.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
