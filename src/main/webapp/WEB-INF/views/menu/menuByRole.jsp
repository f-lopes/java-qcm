<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="hasRole('ROLE_ADMIN')" >
    <%@include file="../menu/adminMenu.jsp"%>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_TEACHER')" >
    <%@include file="../menu/teacherMenu.jsp"%>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_STUDENT')" >
    <%@include file="../menu/studentMenu.jsp"%>
</sec:authorize>