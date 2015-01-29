<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" action="create" commandName="addQuestionForm">
  <form:errors path="" />
  <div class="form-group">
    <form:label path="label"><spring:message code="question.create.label"/> </form:label>
    <form:input path="label" class="form-control" placeholder="your question"/>
  </div>
  <br/>
  <div class="form-group">
    <form:label path="points"></form:label>
    <form:select path="points" items="${questionsPoints}"></form:select>

  </div>
  <br/>
  <spring:message code="question.create.validate" var="validateForm"/>
  <input type="submit" value="${validateForm}" class="btn btn-primary" />

</form:form>
