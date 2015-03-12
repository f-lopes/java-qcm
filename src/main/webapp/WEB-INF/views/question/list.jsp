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
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <h1>QCM Questions list</h1>

    <table class="table table-striped">
      <thead>
        <tr>
          <td>Name</td>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${questions}" var="question">
          <tr>
            <td>${question.label}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
</div>
</body>
</html>
