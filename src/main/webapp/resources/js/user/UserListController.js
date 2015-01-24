/**
 * Created by lopes_f on 1/22/2015.
 */

var qcmUsersApp = angular.module('qcmUsersApp', ['toggle-switch', 'angular-loading-bar']);

qcmUsersApp.controller('UserListController', function ($scope, $http) {
    var usersUrl = "users/json?showAdminUsers=";
    var showAdminUsers = "false";

    var getUsersRequest = {
        method: 'GET',
        url: usersUrl,
        headers: {
            'Content-Type': "application/json"
        }
    };

    getUsersRequest.url = usersUrl + showAdminUsers;
    $http(getUsersRequest).success(function(data) {
        $scope.users = data;
    });

    $scope.showAdmin = function() {
        showAdminUsers = $scope.switchStatus ? "true" : "false";
        getUsersRequest.url = usersUrl + showAdminUsers;

        $http(getUsersRequest).success(function(data) {
            $scope.users = data;
        }).error(function() {

        });
    };
});