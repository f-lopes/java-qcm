<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form  method="POST" action="add" commandName="addUserForm" class="form-signin" >
    <form:errors path="" />
    <form:label path="email"/>
    <form:input path="email" class="input-block-level" placeholder="Email address"/>
    <form:errors path="email" cssClass="error" />
    <form:label path="password"/>
    <form:password path="password" class="input-block-level" placeholder="Password"/>
    <form:errors path="password" cssClass="error" />
    <form:label path="grade"/>
    <form:select path="grade" id="grades" itemLabel="name" itemValue="id" items="${grades}"/>

    <form:select path="userType" id="userTypes" items="${userTypes}"/>
    <button class="btn btn-large btn-primary" type="submit"><spring:message code="user.add.title"/></button>
</form:form>