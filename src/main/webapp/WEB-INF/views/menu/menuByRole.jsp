<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>"><spring:message code="application.home"/></a></li>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <%@include file="../menu/adminMenu.jsp" %>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_TEACHER')">
                    <%@include file="../menu/teacherMenu.jsp" %>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_STUDENT')">
                    <%@include file="../menu/studentMenu.jsp" %>
                </sec:authorize>

                <li>
                    <a href="<c:url value="?lang=fr"/>">
                        <img src="<c:url value="/resources/img/fr.png"/>" alt="fr"/>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="?lang=en"/>">
                        <img src="<c:url value="/resources/img/en.png"/>" alt="en"/>
                    </a>
                </li>
            </ul>
            <c:url var="logoutURL" value="/logout" />
            <form action="${logoutURL}" method="POST" class="navbar-form navbar-right">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="<spring:message code="deconnexion"/>" class="btn btn-default"/>
            </form>
        </div>
    </div>
</nav>

<!-- Flash -->
<div style="width:1000px; margin:auto;" class="alert alert-${flash.status}">${flash.text}</div>

