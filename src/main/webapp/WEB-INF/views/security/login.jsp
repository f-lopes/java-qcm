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
    <title>QCM++</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container">
    <p style="text-align:center;">

        <img src="../../resources/img/hipsterlogogenerator_1420731148543.png" class="img-rounded" />
    </p>
    
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form action="<c:url value="/login"/>" method="POST" class="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


                <div class="form-group">
                    <label for="login"><spring:message code="email"/></label>
                    <input id="login" type="text" name="login" placeholder="<spring:message code="email"/>" class="form-control"/>
                </div>


                <div class="form-group">
                    <label for="password"><spring:message code="password"/></label>
                    <input id="password" type="password" name="password" placeholder="<spring:message code="password"/>" class="form-control"/>
                </div>

                <input type="submit" value="<spring:message code="login"/>" class="btn btn-success" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
