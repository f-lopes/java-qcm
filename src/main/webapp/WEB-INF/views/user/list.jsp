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

<html ng-app="qcmUsersApp">
<head>
    <title>Users list</title>

    <script src="<c:url value='/resources/js/lib/jquery-2.1.3.min.js'/>"></script>
    <script src="<c:url value='/resources/js/lib/angular.min.js'/>"></script>
    <script src="<c:url value='/resources/js/lib/angular-toggle-switch.min.js'/>"></script>
    <script src="<c:url value='/resources/js/lib/loading-bar.js'/>"></script>
    <script src="<c:url value='/resources/js/user/UserListController.js'/>"></script>

    <link rel="stylesheet" href="<c:url value='/resources/css/angular-toggle-switch.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/loading-bar.css'/>"/>


    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body ng-controller="UserListController">

    <div class="container">

        <sec:authorize access="isAuthenticated()" >
            <%@include file="../menu/menuByRole.jsp"%>
        </sec:authorize>

        <h1><spring:message code="users.list" /> </h1>


        <div class="row">
            <div class="col-md-8">
                <input ng-model="usernameQuery" type="text" placeholder="<spring:message code="users.filter"/>" class="form-control"/>
                <input type="hidden"id="usersURL" value="<c:url value='/users/json?showAdminUsers=' />"/>
            </div>
            <div class="col-md-2">
                <toggle-switch ng-click="showAdmin()" ng-model="switchStatus" knob-label="Admins/teachers" style="height: 25px"/>
            </div>
            <div class="col-md-2">
                <p><a href="<c:url value="/users/add"/>" class="btn btn-success"><spring:message code="user.add.title"/></a></p>
            </div>
        </div>
        
        <br />

        <table class="table">
            <thead>
            <tr>
                <td>Name</td>
                <td>First name</td>
                <td>Email</td>
                <td>Type</td>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="user in users | filter:usernameQuery">
                <td>{{user.lastName}}</td>
                <td>{{user.firstName}}</td>
                <td>{{user.email}}</td>
                <td>{{user.roles[0].name}}</td>
            </tr>
            </tbody>
        </table>


    </div>

</body>
</html>
