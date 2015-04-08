<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">


        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>
        
        <h1><spring:message code="question.edit"/></h1>


        <%@include file="editQuestionForm.jsp"%>
    </div>
</body>
</html>
