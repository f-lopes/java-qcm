<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>QCM Questions list</title>
</head>
<body>

<h1>QCM Questions list</h1>

<table>
  <thead>
    <tr>
      <td>ID</td>
      <td>Name</td>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="qcm.questions" var="question">
      <tr>
        <td>${question.id}</td>
        <td>${question.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
