<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="editQuestionURL" value="/qcm/${questionForm.qcmId}/questions/edit"/>

<form:form method="POST" action="${editQuestionURL}" commandName="questionForm" cssClass="form-inline">
  <form:errors path="" />
  <div class="form-group">
    <form:hidden path="id"/>
    <form:label path="label"><spring:message code="question.edit"/> </form:label>
    <spring:message code='question.label' var="placeholderLabel"/>
    <form:input path="label" class="form-control" placeholder="${placeholderLabel}"/>
  </div>
    
  <spring:message code="question.edit" var="validateForm"/>
  <input type="submit" value="${validateForm}" class="btn btn-primary" />

</form:form>
