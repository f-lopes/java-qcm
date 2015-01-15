<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 15/01/2015
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title><spring:message code="qcm.view.title"/></title>
</head>
<body>
<h1><c:out value="${qcm.name}"></c:out></h1>

<%@include file="qcmViewForm.jsp"%>
</body>
</html>
