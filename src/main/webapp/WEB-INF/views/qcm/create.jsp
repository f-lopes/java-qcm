<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/8/2015
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<h1><spring:message code="qcm.create.title"/></h1>

<!-- Flash -->
<div style="width:1000px; margin:auto;" class="alert alert-${flash.status}">${flash.text}</div>

<%@include file="qcmForm.jsp"%>

</body>
</html>
