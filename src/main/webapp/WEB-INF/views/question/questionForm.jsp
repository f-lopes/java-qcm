<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="POST" action="create" commandName="addQuestionForm" cssClass="form-inline">
  <form:errors path="" />
  <div class="form-group">
    <form:label path="label"><spring:message code="question.create.label"/> </form:label>
    <spring:message code='question.label' var="placeholderLabel"/>
    <form:input path="label" class="form-control" placeholder="${placeholderLabel}"/>
  </div>
    
  <spring:message code="question.create.validate" var="validateForm"/>
  <input type="submit" value="${validateForm}" class="btn btn-primary" />

</form:form>
