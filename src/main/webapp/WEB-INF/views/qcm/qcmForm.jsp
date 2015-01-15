<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<form:form method="POST" action="create" commandName="qcmForm">
   <table>
        <tr>
            <td><label>Name :</label></td>
            <td><form:input path="qcmName" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Créer le QCM"/>
            </td>
        </tr>
    </table>
</form:form>