<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>QCM</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <h1>QCM</h1>

    <h3><spring:message code="qcm.name"/> : ${qcm.name}</h3>
    <h3><spring:message code="qcm.questions.number"/> : ${questions.size()}</h3>

    <br/><br/>
    <c:if test="${questions.size() > 0}" >
        <table>
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
                    <td><h3>${question.label}</h3></td>
                    <td>${question.points}</td>
                    <td><a href="<c:url value="${qcm.id}/questions/${question.id}/answers"/>"><spring:message code="question.edit"/></a></td>
                <%--<ul>--%>
                    <%--<c:forEach items="${question.answers}" var="answer">--%>
                        <%--<li>${answer.content}</li>--%>
                    <%--</c:forEach>--%>
                <%--</ul>--%>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </c:if>
    <br/><br/>
<a href="<c:url value="${qcm.id}/questions/create"/>"><spring:message code="question.create.title"/></a>
