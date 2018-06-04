/*
 *---------------------- Routage de angular JS ---------------------------------
 */
var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "TempsReelServlet"
            })
            .when("/pointage", {
                templateUrl: "PointagesServlet"
            })
            .when("/entrees", {
                templateUrl: "EntreesServlet"
            })
            .when("/visites", {
                templateUrl: "VisitesServlet"
            })
            .when("/profils", {
                templateUrl: "UtilisateurProfilsServlet"
            })
            .when("/individus", {
                templateUrl: "IndividusServlet"
            })
            .when("/collaborateurs", {
                templateUrl: "EmployesServlet"
            })
            .when("/postes", {
                templateUrl: "PostesServlet"
            })
            .when("/enseignants", {
                templateUrl: "EnseignantsServlet"
            })
            .when("/etudiants", {
                templateUrl: "EtudiantsServlet"
            })
            .when("/anneeacademique", {
                templateUrl: "AnneeScolairesServlet"
            })
            .when("/filieres", {
                templateUrl: "FilieresServlet"
            })
            .when("/matieres", {
                templateUrl: "MatieresServlet"
            })
            .when("/coursall", {
                templateUrl: "CourssServlet"
            })
            .when("/niveaux", {
                templateUrl: "NiveauEtudesServlet"
            })
            .when("/periodescreuses", {
                templateUrl: "PeriodeCreusesServlet"
            })
            .when("/badges", {
                templateUrl: "BadgesServlet"
            })
            .when("/utilisateurs", {
                templateUrl: "UtilisateursServlet"
            })
});
/*----------------------------------------------------------------------------*/


