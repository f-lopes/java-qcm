<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/26/2015
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>

<c:url var="addAnswerURL" value="/qcm/${qcmId}/questions/${questionId}/answers/add"/>
<form:form method="POST" action="${addAnswerURL}" commandName="addAnswerForm" cssClass="form">
    <form:errors path="" />
    <div id="answersForm"
        <form:hidden path="questionId"/>
        <div class="" id="answerInputTemplate">

            <div class="form-group">
                <form:label path="content"><spring:message code="qcm.content" /></form:label>
                <form:input path="content" class="form-control" required="required"/>
                <form:errors path="content" cssClass="error" />
            </div>

            <div class="form-group">
                <form:label path="answerRate"><spring:message code="qcm.rate" /></form:label>
                <form:input path="answerRate" class="form-control" required="required"/>
                <form:errors path="answerRate" cssClass="error" />
            </div>
        </div>
        <%--<input id="addAnswerButton" type="button" value="<spring:message code="answer.add" />"/>--%>
    </div>
    <input type="submit" class="btn btn-primary" value="<spring:message code="answer.create" />"/>
</form:form>
