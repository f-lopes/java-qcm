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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><spring:message code="evaluations.available"/></title>
</head>
<body>

<h1><spring:message code="evaluations.available"/></h1>

<c:choose>
    <c:when test="${fn:length(evaluations) gt 0}">

        <table>
            <thead>
            <tr>
                <td><spring:message code="evaluation.startDate"/></td>
                <td><spring:message code="evaluation.endDate"/></td>
                <td><spring:message code="course"/></td>
                <td>Average</td>
                <td><spring:message code="action"/></td>
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
                    <c:choose>
                        <c:when test="${averageMark.get(availableEvaluation) != 'NaN'}">
                            <td>${averageMark.get(availableEvaluation)}</td>
                        </c:when>
                        <c:otherwise>
                            <td><spring:message code="evaluation.finished.average"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <form method="post" action="delete">
                            <input type="hidden" name="evaluationId" value="${availableEvaluation.id}"/>
                            <input type="submit" value="<spring:message code='evaluation.delete'/>" />
                        </form>
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
