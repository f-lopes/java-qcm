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
    <title><spring:message code="courses.list"/></title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

    <div class="container">

        <sec:authorize access="isAuthenticated()" > 
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>

        <h1><spring:message code="courses.list"/></h1>

        <p><a href="<c:url value="/courses/add"/>" class="btn btn-success"><spring:message code="course.create.title"/></a></p>

        <c:choose>
          <c:when test="${fn:length(courses) gt 0}">

            <table class="table table-striped">
              <thead>
              <tr>
                <td><spring:message code="course.name"/></td>
                <td><spring:message code="action"/></td>
              </tr>
              </thead>
              <tbody>
              <c:forEach items="${courses}" var="course">
                <tr>
                  <td>${course.name}</td>
                  <td>
                      <form method="post" action="<c:url value='/courses/delete'/>">
                          <input type="hidden" name="courseId" value="${course.id}"/>
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                          <input  class="btn btn-danger" type="submit" value="<spring:message code='course.delete'/>" />
                      </form>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>

          </c:when>

          <c:otherwise>
            <spring:message code="no.course.found"/>
          </c:otherwise>

        </c:choose>
    </div>
</body>
</html>
