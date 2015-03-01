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
    <title></title>
</head>
<body>

<h1><spring:message code="answer.list.title"/></h1>
<c:if test="${answers.size() > 0}">
  <table>
    <thead>
    <tr>
      <td>ID</td>
      <td>Name</td>
      <td>Content</td>
      <td>Rate</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${answers}" var="answer">
      <tr>
        <td>${answer.id}</td>
        <td>${answer.content}</td>
        <td>${answer.answerRate}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<br/><br/>

<a href="<c:url value="answers/add"/>"><spring:message code="answer.create"/></a>
</body>
</html>
