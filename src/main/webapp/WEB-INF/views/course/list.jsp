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
    <title><spring:message code="courses.list"/></title>
</head>
<body>

<h1><spring:message code="courses.list"/></h1>

<c:choose>
  <c:when test="${fn:length(courses) gt 0}">

    <table>
      <thead>
      <tr>
        <td><spring:message code="course.name"/></td>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${courses}" var="course">
        <tr>
          <td>${course.name}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </c:when>

  <c:otherwise>
    <spring:message code="no.course.found"/>
  </c:otherwise>

</c:choose>
</body>
</html>