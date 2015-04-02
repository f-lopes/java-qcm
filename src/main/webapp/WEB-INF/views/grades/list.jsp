<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"/>
    <title><spring:message code="grades.list"/></title>
</head>
<body>


<div class="container">
    <sec:authorize access="isAuthenticated()">
        <%@include file="../menu/menuByRole.jsp" %>
    </sec:authorize>

    <h1><spring:message code="grades.list"/></h1>
    <c:if test="${grades.size() > 0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <td>Name</td>
                <td>See users</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${grades}" var="grade">
                <tr>
                    <td>${grade.name}</td>
                    <td>
                        <a href="<c:url value="/student/all?grade=${grade.name}"/>"><spring:message
                                code="students.list"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <br/><br/>

</div>
</body>
</html>
