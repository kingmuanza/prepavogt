/*
 *---------------------- Routage de angular JS ---------------------------------
 */

function UrlExists(url) {
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    if (http.status !== 404 && http.status !== 500){
        return url;
    }else{
        return "notfound.jsp";
    }
        
}
var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/tempsreel", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/test", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/pointage", {
                templateUrl: UrlExists("PointagesServlet")
            })
            .when("/entrees", {
                templateUrl: UrlExists("EntreesServlet")
            })
            .when("/visites", {
                templateUrl: UrlExists("VisitesServlet")
            })
            .when("/profils", {
                templateUrl: UrlExists("UtilisateurProfilsServlet")
            })
            .when("/individus", {
                templateUrl: UrlExists("IndividusServlet")
            })
            .when("/collaborateurs", {
                templateUrl: UrlExists("EmployesServlet")
            })
            .when("/postes", {
                templateUrl: UrlExists("PostesServlet")
            })
            .when("/enseignants", {
                templateUrl: UrlExists("EnseignantsServlet")
            })
            .when("/etudiants", {
                templateUrl: UrlExists("EtudiantsServlet")
            })
            .when("/anneeacademique", {
                templateUrl: UrlExists("AnneeScolairesServlet")
            })
            .when("/filieres", {
                templateUrl: UrlExists("FilieresServlet")
            })
            .when("/matieres", {
                templateUrl: UrlExists("MatieresServlet")
            })
            .when("/coursall", {
                templateUrl: UrlExists("CourssServlet")
            })
            .when("/niveaux", {
                templateUrl: UrlExists("NiveauEtudesServlet")
            })
            .when("/periodescreuses", {
                templateUrl: UrlExists("PeriodeCreusesServlet")
            })
            .when("/badges", {
                templateUrl: UrlExists("BadgesServlet")
            })
            .when("/utilisateurs", {
                templateUrl: UrlExists("UtilisateursServlet")
            })
            .when("/statistiques", {
                templateUrl: UrlExists("StatistiquesServlet")
            })
            .when("/profil", {
                templateUrl: UrlExists("test.jsp")
            })
            .when("/enregistrementIndividu", {
                templateUrl: UrlExists("enregistrementIndividuServlet")
            })
            .otherwise({
                redirectTo: '/'
            });
});
/*----------------------------------------------------------------------------*/


