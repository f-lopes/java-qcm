<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 2/12/2015
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Marks</title>
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <div class="container">

        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>


        <c:choose>
            <c:when test="${fn:length(results) gt 0}">

                <ul>
                    <c:forEach var="result" items="${results}">
                        <li>
                            ${result.evaluation.course.name} : <c:out value="${result.mark}"/> -
                                <fmt:formatDate pattern="yyyy-MM-dd" value="${result.mark}" /> -
                            ${result.evaluation.teacher.name} ${result.evaluation.teacher.firstName}
                        </li>
                    </c:forEach>
                </ul>
                <spring:message code="student.average"/> : ${average}

            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">
                    <spring:message code="no.result.found"/>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
