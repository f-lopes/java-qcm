<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/16/2015
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form:form method="POST" action="create" commandName="createEvaluationForm">
  <div class="form-group">
    <form:label path="evaluationName">Name</form:label>
    <form:input path="evaluationName" class="form-control" />
    <form:errors path="evaluationName" cssClass="error" />
  </div>

  <div class="form-group">
    <form:label path="evaluationQcm"><spring:message code="evaluation.selected.qcm"/></form:label>
    <form:select path="evaluationQcm" items="${qcmList}" itemValue="id" itemLabel="qcmName" cssClass="form-control"/>
    <form:errors path="evaluationQcm" cssClass="error" />
  </div>

  <div class="form-group">
    <input type="submit" class="btn btn-primary" value="<spring:message code="qcm.create"/>"/>
  </div>
  </table>
</form:form>