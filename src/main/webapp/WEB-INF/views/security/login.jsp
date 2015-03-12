<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 3/12/2015
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="<c:url value="/login"/>" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <label for="login"><spring:message code="email"/></label>
    <input id="login" type="text" name="login"/>

    <label for="password"><spring:message code="password"/></label>
    <input id="password" type="password" name="password"/>

    <input type="submit" value="<spring:message code="login"/>" />
</form>

</body>
</html>
