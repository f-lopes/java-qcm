<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/26/2015
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" action="create" commandName="qcmForm">
  <form:errors path="" />
  <div id="answersForm">
    <form:label path="name">Name</form:label>
    <form:input path="name" id="answerInputTemplate" class="form-control"/>
    <form:errors path="name" cssClass="error" />
    <input id="addAnswerButton" type="button" value="add answer"/>
  </div>
  <div class="form-group">
    <input type="submit" class="btn btn-primary" value="<spring:message code="qcm.create"/>"/>
  </div>
</form:form>

