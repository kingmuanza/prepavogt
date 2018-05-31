/*
 *---------------------- Routage de angular JS ---------------------------------
 */
var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "test.jsp"
            })
            .when("/menu", {
                templateUrl: "test2.jsp"
            })
            .when("/utilisateurs", {
                templateUrl: "UtilisateursServlet"
            })
});
/*----------------------------------------------------------------------------*/

