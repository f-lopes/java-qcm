<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/24/2015
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" action="add" commandName="addCourseForm">
  <form:errors path="" />
  <div class="form-group">
    <form:label path="name">Name</form:label>
    <form:input path="name" class="form-control" />
    <form:errors path="name" cssClass="error" />
  </div>
  <div class="form-group">
    <input type="submit" class="btn btn-primary" value="<spring:message code="course.create.title"/>"/>
  </div>
</form:form>