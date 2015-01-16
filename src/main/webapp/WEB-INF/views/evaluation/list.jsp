list.jsp<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="qcm.list"/></title>
</head>
<body>

<h1>Evaluations list</h1>

<c:choose>
  <c:when test="evaluations.size > 0">

    <table>
      <thead>
      <tr>
        <td><spring:message code="qcm.name"/></td>
        <td><spring:message code="qcm.questions.number"/></td>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="evaluations" var="evaluation">
        <tr>
          <td>${evaluation.name}</td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

  </c:when>

  <c:otherwise>
    <spring:message code="no.qcm.found"/>
  </c:otherwise>

</c:choose>
</body>
</html>
