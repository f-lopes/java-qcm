<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/8/2015
  Time: 5:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qcm" uri="/WEB-INF/taglib/profilecondition.tld" %>
<html>
<head>
    <title>QCM++</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>


<div class="container">

    <sec:authorize access="isAnonymous()" >

        <p style="text-align:center;">

            <img src="resources/img/hipsterlogogenerator_1420731148543.png" class="img-rounded" />
            <br />

            <c:url var="loginURL" value="/secure/login" />
            <a href="${loginURL}" class="btn btn-default btn-lg">Connexion</a>

        </p>

    </sec:authorize>

    <sec:authorize access="isAuthenticated()" >
        <%@include file="../menu/menuByRole.jsp"%>
    </sec:authorize>

    <qcm:profiles value="dev,docker">
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
    </qcm:profiles>

    ${gitCommitId}

</div>
</body>
</html>
