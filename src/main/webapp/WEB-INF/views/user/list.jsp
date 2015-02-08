<%--
  Created by IntelliJ IDEA.
  User: lopes_f
  Date: 1/15/2015
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html ng-app="qcmUsersApp" >
<head>
  <title>Users list</title>

  <script src="<c:url value='/resources/js/lib/angular.min.js'/>"></script>
  <script src="<c:url value='/resources/js/lib/angular-toggle-switch.min.js'/>"></script>
  <script src="<c:url value='/resources/js/lib/loading-bar.js'/>"></script>
  <script src="<c:url value='/resources/js/user/UserListController.js'/>"></script>

  <link rel="stylesheet" href="<c:url value='/resources/css/angular-toggle-switch.css'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/css/loading-bar.css'/>"/>
</head>
<body ng-controller="UserListController">

<h1>Users list</h1>
<spring:message code="users.filter"/>
<input ng-model="usernameQuery" type="text"/>


  <table>
    <thead>
    <tr>
      <td>ID</td>
      <td>Name</td>
      <td>First name</td>
      <td>Email</td>
      <td>Type</td>
    </tr>
    </thead>
    <tbody>
    <tr ng-repeat="user in users | filter:usernameQuery">
      <td>{{user.id}}</td>
      <td>{{user.lastName}}</td>
      <td>{{user.firstName}}</td>
      <td>{{user.email}}</td>
      <td>{{user.roles[0].name}}</td>
    </tr>
    </tbody>
  </table>

<toggle-switch ng-click="showAdmin()" ng-model="switchStatus" knob-label="Admins/teachers" style="max-height: 20%"/>

</body>
</html>
