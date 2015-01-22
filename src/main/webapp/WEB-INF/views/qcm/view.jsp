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
</head>
<body>
    <h1>QCM</h1>

    <h3><spring:message code="qcm.name"/> : ${qcm.name}</h3>
    <h3><spring:message code="qcm.questions.number"/> : ${qcm.questions.size()}</h3>

    <br/><br/>
    <ol>
        <c:forEach items="${qcm.questions}" var="question">

            <li><h3>${question.label}</h3></li>
            <ul>
                <c:forEach items="${question.answers}" var="answer">
                    <li>${answer.content}</li>
                </c:forEach>
            </ul>
            <br/>
        </c:forEach>
    </ol>
</body>
</html>
