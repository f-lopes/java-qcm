<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="hasRole('student')">

    <ul>
        <li>Evaluations</li>
        <li>Notes</li>
        <li>Profile</li>
    </ul>

</sec:authorize>