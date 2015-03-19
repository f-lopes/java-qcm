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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><spring:message code="evaluations.available"/></title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

    <div class="container">

        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>

        <h1><spring:message code="evaluations.available"/></h1>


        <sec:authorize access="hasRole('ROLE_TEACHER')">
            <p><a href="<c:url value="/evaluations/create"/>" class="btn btn-success"><spring:message code="evaluation.create.title"/></a></p>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <spring:message code="evaluations.by-grade"/> : <br/>
            <c:forEach items="${grades}" var="grade">
                <a href="<c:url value="/evaluations/by-grade?grade=${grade.name}"/>">${grade.name}</a> <br/>
            </c:forEach>
        </sec:authorize>
        
        <c:choose>
            <c:when test="${fn:length(evaluations) gt 0}">

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td><spring:message code="evaluation.startDate"/></td>
                        <td><spring:message code="evaluation.endDate"/></td>
                        <td><spring:message code="course"/></td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')" >
                            <td><spring:message code="action"/></td>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${evaluations}" var="availableEvaluation">
                        <tr>
                            <td>
                                <fmt:formatDate value="${availableEvaluation.startDate}" pattern="dd/MM/yyyy"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${availableEvaluation.endDate}" pattern="dd/MM/yyyy"/>
                            </td>
                            <td>${availableEvaluation.course.name}</td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')" >
                                    <form method="post" action="<c:url value="/evaluations/delete" />">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="evaluationId" value="${availableEvaluation.id}"/>
                                        <input type="submit" value="<spring:message code='evaluation.delete'/>" class="btn btn-danger" />
                                    </form>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </c:when>

            <c:otherwise>
                <div class="alert alert-danger">
                    <spring:message code="no.evaluation.found"/>
                </div>
            </c:otherwise>

        </c:choose>


    </div>

</body>
</html>
