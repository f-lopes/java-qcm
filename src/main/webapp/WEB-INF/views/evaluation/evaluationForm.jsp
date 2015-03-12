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
  <form:errors path="" cssClass="error" />
  <div class="form-group">
    <form:label path="evaluationName"><spring:message code="evaluation.name"/></form:label>
    <form:input path="evaluationName" class="form-control" />
    <form:errors path="evaluationName" cssClass="error" />
  </div>
    
    <div class="row">
        <div class="col-md-10">
            <div class="form-group">
                <form:label path="evaluationQcm"><spring:message code="evaluation.selected.qcm"/></form:label>
                <form:select path="evaluationQcm" items="${qcmList}" itemValue="id" itemLabel="name" cssClass="form-control"/>
                <form:errors path="evaluationQcm" cssClass="error" />
            </div>
        </div>
        <div class="col-md-2">
            <div class="form-group">
                <label>&nbsp;</label>
                <p><a style="text-align:right;" href="<c:url value="/qcm/create"/>" class="btn btn-success"><spring:message code="qcm.create"/></a></p>
            </div>
        </div>
    </div>

  <div class="form-group">
    <form:label path="startDate"><spring:message code="evaluation.startDate"/></form:label>
    <form:input path="startDate" id="startDate" class="form-control" />
    <form:errors path="startDate" cssClass="error" />
  </div>

  <div class="form-group">
    <form:label path="endDate"><spring:message code="evaluation.endDate"/></form:label>
    <form:input path="endDate" id="endDate" class="form-control" />
    <form:errors path="endDate" cssClass="error" />
  </div>

  <div class="form-group">
    <form:label path="grade"><spring:message code="evaluation.selected.grade"/></form:label>
    <form:select path="grade" items="${grades}" itemValue="id" itemLabel="name" cssClass="form-control"/>
    <form:errors path="grade" cssClass="error" />
  </div>

  <div class="form-group">
    <form:label path="course"><spring:message code="evaluation.selected.course"/></form:label>
    <form:select path="course" items="${courses}" itemValue="id" itemLabel="name" cssClass="form-control"/>
    <form:errors path="course" cssClass="error" />
  </div>

  <div class="form-group">
    <input type="submit" class="btn btn-primary" value="<spring:message code="evaluation.create.title"/>"/>
  </div>
</form:form>
