<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 6:05 PM
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
    
    <h1><spring:message code="answer.list.title"/></h1>
    <c:if test="${answers.size() > 0}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td>Content</td>
                    <td>Rate</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${answers}" var="answer">
                    <tr>
                        <td>${answer.content}</td>
                        <td>${answer.answerRate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <br/>
    <br/>
    
    <a class="btn btn-success" href="<c:url value="/qcm/${qcm.id}/questions/${question.id}/answers/add"/>"><spring:message code="answer.create"/></a>
</div>
</body>
</html>
