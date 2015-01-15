<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<form:form method="POST" action="create" commandName="qcmForm" class="form">
    <div class="form-group">
        <label for="qcmName">Nom :</label>
        <form:input path="qcmName" class="form-control" placeholder="Nom de votre QCM" />
    </div>
    <input type="submit" value="Cr&eacute;er le QCM" class="btn btn-primary"/>
</form:form>