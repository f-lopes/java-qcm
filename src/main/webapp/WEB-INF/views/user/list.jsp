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
    <title>Users list</title>
</head>
<body>

<h1>Users list</h1>

<table>
  <thead>
    <tr>
      <td>ID</td>
      <td>Name</td>
      <td>First name</td>
      <td>Email</td>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${users}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.lastName}</td>
        <td>${user.firstName}</td>
        <td>${user.email}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>

</body>
</html>
