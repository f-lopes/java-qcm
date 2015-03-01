<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 3/1/2015
  Time: 3:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="access-denied"/></title>
</head>
<body>
<spring:message code="access-denied"/>

<a href="<c:url value="/"/>"><spring:message code="home"/></a>
<br/>

<%@include file="menu/menuByRole.jsp"%>

</body>
</html>
