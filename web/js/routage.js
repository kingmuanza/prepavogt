/*
 *---------------------- Routage de angular JS ---------------------------------
 */

function UrlExists(url) {
    var http = new XMLHttpRequest();
    http.open('HEAD', url, false);
    http.send();
    if (http.status !== 404 && http.status !== 500) {
        return url;
    } else {
        return "notfound.jsp";
    }

}
var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: UrlExists("AujourdhuiServlet")
            })
            .when("/statsentrees", {
                templateUrl: UrlExists("StatsEntreesServlet")
            })
            .when("/configuration", {
                templateUrl: UrlExists("ConfigurationServlet")
            })
            .when("/importer", {
                templateUrl: UrlExists("ImporterServlet")
            })
            .when("/exporter", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/tempsreel", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/test", {
                templateUrl: UrlExists("TempsReelServlet")
            })
            .when("/pointage", {
                templateUrl: function (params) {
                    return UrlExists("PointageServlet")
                }
            })
            .when("/pointage/:id", {
                templateUrl: function (params) {
                    return UrlExists("PointageServlet")+"?id=" + params.id
                }
            })
            .when("/pointages", {
                templateUrl: UrlExists("PointagesServlet")
            })
            .when("/entree", {
                templateUrl: function (params) {
                    return UrlExists("EntreeServlet")
                }
            })
            .when("/entree/:id", {
                templateUrl: function (params) {
                    return UrlExists("EntreeServlet")+"?id=" + params.id
                }
            })
            .when("/entrees", {
                templateUrl: UrlExists("EntreesServlet")
            })
            .when("/visite", {
                templateUrl: function (params) {
                    return UrlExists("VisiteServlet")
                }
            })
            .when("/visite/:id", {
                templateUrl: function (params) {
                    return UrlExists("VisiteServlet")+"?id=" + params.id
                }
            })
            .when("/visites", {
                templateUrl: UrlExists("VisitesServlet")
            })
            .when("/profil", {
                templateUrl: function (params) {
                    return UrlExists("UtilisateurProfilServlet")
                }
            })
            .when("/profil/:id", {
                templateUrl: function (params) {
                    return UrlExists("UtilisateurProfilServlet")+"?id=" + params.id
                }
            })
            .when("/profils", {
                templateUrl: UrlExists("UtilisateurProfilsServlet")
            })
            .when("/individu", {
                templateUrl: function (params) {
                    return UrlExists("IndividuServlet")
                }
            })
            .when("/individu/:id", {
                templateUrl: function (params) {
                    return UrlExists("IndividuServlet")+"?id=" + params.id
                }
            })
            .when("/individus", {
                templateUrl: UrlExists("IndividusServlet")
            })
            .when("/collaborateur", {
                templateUrl: function (params) {
                    return UrlExists("EmployeServlet")
                }
            })
            .when("/collaborateur/:id", {
                templateUrl: function (params) {
                    return UrlExists("EmployeServlet")+"?id=" + params.id
                }
            })
            .when("/collaborateurs", {
                templateUrl: UrlExists("EmployesServlet")
            })
            .when("/poste", {
                templateUrl: function (params) {
                    return UrlExists("PosteServlet")
                }
            })
            .when("/poste/:id", {
                templateUrl: function (params) {
                    return UrlExists("PosteServlet")+"?id=" + params.id
                }
            })
            .when("/postes", {
                templateUrl: UrlExists("PostesServlet")
            })
            .when("/enseignant", {
                templateUrl: function (params) {
                    return UrlExists("EnseignantServlet")
                }
            })
            .when("/enseignant/:id", {
                templateUrl: function (params) {
                    return UrlExists("EnseignantServlet")+"?id=" + params.id
                }
            })
            .when("/enseignants", {
                templateUrl: UrlExists("EnseignantsServlet")
            })
            .when("/etudiant", {
                templateUrl: function (params) {
                    return UrlExists("EtudiantServlet")
                }
            })
            .when("/etudiant/:id", {
                templateUrl: function (params) {
                    return UrlExists("EtudiantServlet")+"?id=" + params.id
                }
            })
            .when("/etudiants", {
                templateUrl: UrlExists("EtudiantsServlet")
            })
            .when("/anneeacademique", {
                templateUrl: function (params) {
                    return UrlExists("AnneeScolaireServlet")
                }
            })
            .when("/anneeacademique/:id", {
                templateUrl: function (params) {
                    return UrlExists("AnneeScolaireServlet")+"?id=" + params.id
                }
            })
            .when("/anneeacademiques", {
                templateUrl: UrlExists("AnneeScolairesServlet")
            })
            .when("/filiere", {
                templateUrl: function (params) {
                    return UrlExists("FiliereServlet")
                }
            })
            .when("/filiere/:id", {
                templateUrl: function (params) {
                    return UrlExists("FiliereServlet")+"?id=" + params.id
                }
            })
            .when("/filieres", {
                templateUrl: UrlExists("FilieresServlet")
            })
            .when("/matiere", {
                templateUrl: function (params) {
                    return UrlExists("MatiereServlet")
                }
            })
            .when("/matiere/:id", {
                templateUrl: function (params) {
                    return UrlExists("MatiereServlet")+"?id=" + params.id
                }
            })
            .when("/matieres", {
                templateUrl: UrlExists("MatieresServlet")
            })
            .when("/cours", {
                templateUrl: function (params) {
                    return UrlExists("CoursServlet")
                }
            })
            .when("/cours/:id", {
                templateUrl: function (params) {
                    return UrlExists("CoursServlet")+"?id=" + params.id
                }
            })
            .when("/coursall", {
                templateUrl: UrlExists("CourssServlet")
            })
            .when("/niveau", {
                templateUrl: function (params) {
                    return UrlExists("NiveauEtudeServlet")
                }
            })
            .when("/niveau/:id", {
                templateUrl: function (params) {
                    return UrlExists("NiveauEtudeServlet")+"?id=" + params.id
                }
            })
            .when("/niveaux", {
                templateUrl: UrlExists("NiveauEtudesServlet")
            })
            .when("/periodecreuse", {
                templateUrl: function (params) {
                    return UrlExists("PeriodeCreuseServlet")
                }
            })
            .when("/periodecreuse/:id", {
                templateUrl: function (params) {
                    return UrlExists("PeriodeCreuseServlet")+"?id=" + params.id
                }
            })
            .when("/periodescreuses", {
                templateUrl: UrlExists("PeriodeCreusesServlet")
            })
            .when("/badge", {
                templateUrl: function (params) {
                    return UrlExists("BadgeServlet")
                }
            })
            .when("/badge/:id", {
                templateUrl: function (params) {
                    return UrlExists("BadgeServlet")+"?id=" + params.id
                }
            })
            .when("/badges", {
                templateUrl: UrlExists("BadgesServlet")
            })
            .when("/utilisateur", {
                templateUrl: function (params) {
                    return UrlExists("UtilisateurServlet")
                }
            })
            .when("/utilisateur/:id", {
                templateUrl: function (params) {
                    return UrlExists("UtilisateurServlet")+"?id=" + params.id
                }
            })
            .when("/utilisateurs", {
                templateUrl: UrlExists("UtilisateursServlet")
            })
            .when("/statistiques", {
                templateUrl: UrlExists("StatistiquesServlet")
            })
            .otherwise({
                redirectTo: '/'
            });
});
/*----------------------------------------------------------------------------*/


