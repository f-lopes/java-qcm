<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" action="create" commandName="addQuestionForm">
  <form:errors path="" />
  <div class="form-group">
    <form:label path="label"></form:label>
    <form:input path="label" class="form-control" />
  </div>
  <div class="form-group">

    <form:label path="points"></form:label>
    <form:select path="points" items="${questionsPoints}"></form:select>

  </div>

  <input type="submit" value="Créer" class="btn btn-primary" />

</form:form>
