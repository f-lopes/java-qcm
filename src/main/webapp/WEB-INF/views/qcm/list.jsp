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
    <title>QCM list</title>
</head>
<body>

<h1>QCM list</h1>

<table>
  <thead>
    <tr>
      <td>ID</td>
      <td>Name</td>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="qcmList" var="qcm">
      <tr>
        <td>${qcm.id}</td>
        <td>${qcm.name}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
