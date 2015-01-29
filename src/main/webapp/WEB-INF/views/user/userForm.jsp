<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<form:form  method="POST" action="add" commandName="addUserForm" class="form-signin form" >

    <form:errors path="" />

    <div class="form-group">
        <form:label path="email"/>
        <form:input path="email" class="input-block-level form-control" placeholder="Email address"/>
        <form:errors path="email" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="firstName"/>
        <form:input path="firstName" class="input-block-level form-control" placeholder="Prénom"/>
        <form:errors path="firstName" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="name"/>
        <form:input path="name" class="input-block-level form-control" placeholder="Nom"/>
        <form:errors path="name" cssClass="error" />
    </div>

    <div class="form-group">
        <form:label path="password"/>
        <form:password path="password" class="input-block-level form-control" placeholder="Password"/>
        <form:errors path="password" cssClass="error" />
    </div>

    <div class="form-group">
        <form:select path="userType" id="userTypes" items="${userTypes}" class="form-control"/>
    </div>

    <div class="form-group">
        <form:label path="grade"/>
        <form:select path="grade" id="grades"  itemLabel="name" itemValue="id" items="${grades}"  class="form-control"/>
    </div>

    <button class="btn btn-large btn-primary" type="submit"><spring:message code="user.add.title"/></button>
</form:form>