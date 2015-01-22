<%--
  Created by IntelliJ IDEA.
  User: Simon
  Date: 22/01/2015
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Evaluation</title>
</head>
<body>
  <form action="take" method="post">
    <input type="hidden" name="evaluationId" value="${evaluation.id}" />
    <input type="submit" value="Take"/>
  </form>
</body>
</html>
