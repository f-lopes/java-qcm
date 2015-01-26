<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/26/2015
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<script src="<c:url value='/resources/js/lib/jquery-2.1.3.min.js'/>"></script>

<script>

  jQuery(document).ready(function () {
    var nbInput = 1;
    var $answerInputTemplate = jQuery("#answerInputTemplate");
    var $answersForm = jQuery("#answersForm");

    var registerListeners = function() {
      jQuery("#addAnswerButton").click(function() {
        var $newAnswerInput = $answerInputTemplate.clone();
        $newAnswerInput.attr("name", "answer[" + (++nbInput - 1) + "]");
        $newAnswerInput.appendTo($answersForm);
      });
    }

    registerListeners();
  });

</script>
</body>
</html>
