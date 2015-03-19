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

        <c:choose>
          <c:when test="${fn:length(availableEvaluations) gt 0}">


              <sec:authorize access="isAuthenticated()" >
                  <%@include file="../menu/menuByRole.jsp"%>
              </sec:authorize>

            <table class="table table-striped">
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
                  <td>
                      <fmt:formatDate pattern="yyyy-MM-dd" value="${availableEvaluation.key.startDate}" />
                  </td>
                  <td>
                      <fmt:formatDate pattern="yyyy-MM-dd" value="${availableEvaluation.key.endDate}" />
                  </td>
                  <td>
                    ${availableEvaluation.key.course.name}
                  </td>
                    <td>

                        <sec:authorize access="hasRole('ROLE_ADMIN')" >
                            <form method="post" action="<c:url value="/evaluations/delete" />">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="evaluationId" value="${availableEvaluation.key.id}"/>
                                <input type="submit" value="<spring:message code='evaluation.delete'/>" />
                            </form>
                        </sec:authorize>

                        <sec:authorize access="hasRole('ROLE_STUDENT')" >
                            <c:if test="${availableEvaluation.value == null}">
                                <form method="get" action="${takeEvaluationUrl}">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="hidden" name="evaluationId" value="${availableEvaluation.key.id}"/>
                                    <input type="submit" value="<spring:message code='evaluation.take'/>" />
                                </form>
                            </c:if>
                            <c:if test="${availableEvaluation.value != null}">
                                <i><fmt:formatDate pattern="yyyy-MM-dd" value="${availableEvaluation.value.date}" /></i>
                                 - <b><spring:message code="mark"/></b> ${availableEvaluation.value.mark}
                            </c:if>
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
