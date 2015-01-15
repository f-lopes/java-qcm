<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 15/01/2015
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form action="qcm/validate" method="post" commandName="validateQcmForm">
  <form:hidden path="validateQcmForm.id"/>
    <c:forEach items="${qcm.questions}" var="question">
      <h3><spring:message code="qcm.view.question"/> ${question.label}</h3>
      <form:checkboxes path="selectedAnswers" items="question.answers" itemValue="${answer.id}">${answer.content}</form:checkboxes>
  </c:forEach>
</form:form>
