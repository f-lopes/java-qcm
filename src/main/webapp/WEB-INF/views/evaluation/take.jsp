<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 22/01/2015
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
  <title>QCM</title>
</head>
<body>
  <h1>QCM</h1>

  <h3><spring:message code="qcm.name"/> : ${qcm.name}</h3>
  <h3><spring:message code="qcm.questions.number"/> : ${qcm.questions.size()}</h3>

  <br/><br/>
  <%@include file="takeForm.jsp"%>
</body>
</html>
