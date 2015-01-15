<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="POST" action="create">
   <table>
    <tr>
        <td><form:label path="qcmName">Name</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>
</form:form>