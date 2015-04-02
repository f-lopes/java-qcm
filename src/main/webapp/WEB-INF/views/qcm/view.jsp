
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>QCM</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">


        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>
        
        <h1>QCM</h1>
    
        <h3><spring:message code="qcm.name"/> : ${qcm.name}</h3>
        <h3><spring:message code="qcm.questions.number"/> : ${questions.size()}</h3>
    
        <br/><br/>
        
        <c:choose>
            <c:when test="${fn:length(questions) gt 0}" >
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <td><spring:message code="question.label"/></td>
                            <td><spring:message code="question.points"/></td>
                            <td></td>
                        </tr>
                    </thead>
                    <c:forEach items="${questions}" var="question">
                        <tbody>
                        <tr>
                            <td>${question.label}</td>
                            <%--<td>${question.points}</td>--%>
                            <td><a class="btn btn-warning" href="<c:url value="${qcm.id}/questions/${question.id}/answers"/>"><spring:message code="question.edit"/></a></td>
                        <%--<ul>--%>
                            <%--<c:forEach items="${question.answers}" var="answer">--%>
                                <%--<li>${answer.content}</li>--%>
                            <%--</c:forEach>--%>
                        <%--</ul>--%>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:when>

            <c:otherwise>
                <div class="alert alert-warning">
                    <spring:message code="question.none"/>
                </div>
            </c:otherwise>
        </c:choose>
        <br/><br/>
        
        <a class="btn btn-success" href="<c:url value="${qcm.id}/questions/add"/>"><spring:message code="question.create.title"/></a>
    </div>
</body>
</html>