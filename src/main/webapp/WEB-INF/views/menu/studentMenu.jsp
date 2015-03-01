<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/evaluations"/>"><spring:message code="evaluations.available"/></a></li>
                <li><a href="<c:url value="/student/marks"/>"></a><spring:message code="student.marks"/></li>
                <li><a href="<c:url value="/student/personal"/>"><spring:message code="student.info"/></a></li>
            </ul>
        </div>
    </div>
</nav>
