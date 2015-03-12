<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="grades.list"/></title>
</head>
<body>

<h1><spring:message code="grades.list"/></h1>
<c:if test="${answers.size() > 0}">
  <table>
    <thead>
    <tr>
      <td>Name</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${grades}" var="grade">
      <tr>
        <td>${grade.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<br/><br/>

</body>
</html>
