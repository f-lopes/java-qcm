<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form method="POST" action="create" commandName="qcmForm">
    <form:errors path="" />
    <div class="form-group">
        <form:label path="qcmName">Name</form:label>
        <form:input path="qcmName" class="form-control" placeholder="Nom de votre QCM" />
        <form:errors path="qcmName" cssClass="error" />
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-primary" value="<spring:message code="qcm.create"/>"/>
    </div>
</table>
</form:form>