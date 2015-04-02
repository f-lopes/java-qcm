<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 22/01/2015
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="validate" method="post" commandName="validateQcmForm">
  <form:hidden path="qcmId"/>
  <form:hidden path="evalId"/>
  <c:forEach items="${qcm.questions}" var="question">
    <h3><spring:message code="qcm.view.question"/> : ${question.label}</h3>
    <c:set var="answerCounter" value="0"/>
    <c:forEach items="${question.answers}" var="answer">
      ${answer.content}
      <input name="selectedAnswers[${answerCounter}]" type="radio" value="${answer.id}" />
      <c:set var="answerCounter" value="${answerCounter + 1}"/>
    </c:forEach>
    <%--<form:radiobuttons path="selectedAnswers" items="${question.answers}" itemValue="id" itemLabel="content" delimiter="<br/>"/>--%>
  </c:forEach>
  <br/><br/>
  <spring:message code="qcm.view.validate" var="validate"/>
  <input type="submit" name="validate" value="${validate}" />
</form:form>

