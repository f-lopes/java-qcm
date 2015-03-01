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

<html>
<head>
    <title><spring:message code="evaluations.available"/></title>
</head>
<body>

<h1><spring:message code="evaluations.available"/></h1>

<spring:message code="evaluations.by-grade"/> : <br/>
<sec:authorize access="hasRole('ROLE_ADMIN')" >
        <c:forEach items="${grades}" var="grade">
            <a href="<c:url value="/evaluations/by-grade?grade=${grade.name}"/>">${grade.name}</a> <br/>
        </c:forEach>
</sec:authorize>

<c:choose>
    <c:when test="${fn:length(evaluations) gt 0}">

        <table>
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
                    <td>${availableEvaluation.startDate}</td>
                    <td>${availableEvaluation.endDate}</td>
                    <td>${availableEvaluation.course.name}</td>
                    <td>
                        <sec:authorize access="hasRole('ROLE_ADMIN')" >
                            <form method="post" action="<c:url value="/evaluations/delete" />">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="evaluationId" value="${availableEvaluation.id}"/>
                                <input type="submit" value="<spring:message code='evaluation.delete'/>" />
                            </form>
                        </sec:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </c:when>

    <c:otherwise>
        <spring:message code="no.evaluation.found"/>
    </c:otherwise>

</c:choose>
</body>
</html>
