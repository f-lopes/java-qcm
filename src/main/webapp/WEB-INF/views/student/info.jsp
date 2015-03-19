<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 3/11/2015
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
  <h2><spring:message code="student.profile"/> ${currentUser.firstName}</h2>
  <br/>
  <h4><spring:message code="student.details"/></h4>
  <ul>
    <li><spring:message code="student.firstname"/> ${currentUser.firstName}</li>
    <li><spring:message code="student.lastname"/> ${currentUser.lastName}</li>
    <li><spring:message code="student.email"/> ${currentUser.email}</li>
  </ul>
</body>
</html>
