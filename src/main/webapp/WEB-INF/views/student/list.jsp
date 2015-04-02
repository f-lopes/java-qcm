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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html ng-app="qcmUsersApp">
<head>
    <title><spring:message code="students.list"/></title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="<c:url value='/resources/js/lib/jquery-2.1.3.min.js'/>"></script>
</head>
<body >
<div class="container">

    <sec:authorize access="isAuthenticated()" >
        <%@include file="../menu/menuByRole.jsp"%>
    </sec:authorize>
    
    <h1><spring:message code="students.list"/></h1>

    <select id="grade" class="form-control">
        <option value="all"><spring:message code="evaluations.by-grade"/></option>
        <c:forEach items="${grades}" var="grade">
            <c:choose>
                <c:when test="${grade.name eq selected_grade}">
                    <option value="${grade.name}" selected>${grade.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${grade.name}">${grade.name}</option>
                </c:otherwise>
            </c:choose>

        </c:forEach>
    </select>
    <br />
    <c:if test="${grade != null}">
        ${grade}
    </c:if>


    <c:choose>
        <c:when test="${fn:length(students) gt 0}">
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
        </c:when>

        <c:otherwise>
            <div class="alert alert-warning">
                <spring:message code="no.student.found"/>
            </div>
        </c:otherwise>

    </c:choose>
        
</div>


<c:url var="studentsURL" value="/student/all"/>
<input type="hidden" id="url_racine" value="${studentsURL}" />
<script>
    $("#grade").change(function(){
        var value = $(this).val();
        var url = $("#url_racine").val();
        if(value != "all") url = $("#url_racine").val() + "?grade=" + value;
        document.location.href = url;
    });
</script>


</body>
</html>
