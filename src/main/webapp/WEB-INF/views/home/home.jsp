<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/8/2015
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="qcm" uri="/WEB-INF/taglib/qcmurl.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>QCM++</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>

<sec:authorize access="hasRole('ROLE_ADMIN')" >
    <%@include file="../menu/adminMenu.jsp"%>
</sec:authorize>

<div class="container">

    <sec:authorize access="isAnonymous()" >

        <p style="text-align:center;">

            <img src="resources/img/hipsterlogogenerator_1420731148543.png" class="img-rounded" />
            <br />

            <c:url var="loginURL" value="/login" />
            <a href="${loginURL}" class="btn btn-default btn-lg">Connexion</a>

        </p>

    </sec:authorize>
<%--<qcm:url key="user.all"/>--%>


    <form action="login-as-admin" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="login as admin"/>
    </form>

    <form action="login-as-teacher" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="login as teacher"/>
    </form>

    <form action="login-as-student" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="login as student"/>
    </form>

</div>
</body>
</html>
