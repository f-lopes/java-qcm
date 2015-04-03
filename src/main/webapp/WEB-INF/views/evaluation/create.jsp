<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/8/2015
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta http-equiv=Content-Type content="text/html; charset=utf-8" />
      <title><spring:message code="qcm.create.title"/></title>
      <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
      <link rel="stylesheet" href="<c:url value='/resources/jquery/jquery-ui.css'/>"/>
  </head>
  <body>
    <div class="container">


      <sec:authorize access="isAuthenticated()" >
          <%@include file="../menu/menuByRole.jsp"%>
      </sec:authorize>

      <h1><spring:message code="evaluation.create.title"/></h1>

      <%@include file="evaluationForm.jsp"%>

    </div>

    <script type="application/javascript" src="<c:url value='/resources/js/lib/date-picker/external/jquery/jquery.js'/>"></script>
    <script type="application/javascript" src="<c:url value='/resources/js/lib/date-picker/jquery-ui.js'/>"></script>

    <script type="application/javascript">
        $(function() {
            
            $("#startDate").datepicker({
                dateFormat: "dd/mm/yy",
                minDate: new Date()
            });

            $("#endDate").datepicker({
                dateFormat: "dd/mm/yy",
                minDate: new Date()
            })
        });
        
    </script>
  </body>
</html>
