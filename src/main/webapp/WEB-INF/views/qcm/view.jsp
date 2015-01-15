<<<<<<< Updated upstream
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<html>
<head>
    <title>QCM</title>
</head>
<body>

<h1>QCM</h1>

<table>
    <thead>
    <tr>
        <td><spring:message code="qcm.name"/></td>
        <td><spring:message code="qcm.questions.number"/></td>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${qcm.name}</td>
            <td>${qcm.questions.size}</td>
        </tr>
    </tbody>
</table>
</body>
</html>
