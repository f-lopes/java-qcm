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

<html>
<head>
    <title><spring:message code="evaluations.available"/></title>
</head>
<body>

<h1><spring:message code="evaluations.available"/></h1>

<c:choose>
  <c:when test="${fn:length(availableEvaluations) gt 0}">

    <table>
      <thead>
      <tr>
        <td><spring:message code="evaluation.startDate"/></td>
        <td><spring:message code="evaluation.endDate"/></td>
        <td><spring:message code="course"/></td>
        <td><spring:message code="action"/></td>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${availableEvaluations}" var="availableEvaluation">
        <c:url var="takeEvaluationUrl" value="evaluations/take"/>
        <tr>
          <td>${availableEvaluation.key.startDate}</td>
          <td>${availableEvaluation.key.endDate}</td>
          <td>${availableEvaluation.key.course.name}</td>
            <td>
                <c:when test="${availableEvaluation.value == null}">
                    <form method="get" action="${takeEvaluationUrl}">
                        <input type="hidden" name="evaluationId" value="${availableEvaluation.key.id}"/>
                        <input type="submit" value="<spring:message code='evaluation.take'/>" />
                    </form>
                </c:when>
                <c:otherwise>
                    <spring:message code="evaluation.result"/> : ${availableEvaluation.value.mark} - ${availableEvaluation.value.date}
                </c:otherwise>
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
