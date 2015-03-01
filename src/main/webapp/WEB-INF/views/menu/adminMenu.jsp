<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/evaluations/all"/>"><spring:message code="courses.list"/></a></li>
                <li><a href="<c:url value="/evaluations/evaluations/by-grade"/>"></a><spring:message code="evaluations.by-grade"/></li>
                <li><a href="<c:url value="/users/"/>"><spring:message code="users.list"/></a></li>
                <li><a href="<c:url value="/users/add"/>"><spring:message code="user.create.title"/></a></li>
            </ul>
        </div>
    </div>
</nav>