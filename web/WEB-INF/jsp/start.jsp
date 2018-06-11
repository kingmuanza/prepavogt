<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PREPA VOGT</title>

        <!-- Fichiers CSS-->
        <link href="css/semantic.css" rel="stylesheet" type="text/css"/>
        <link href="css/myapp.css?id=23" rel="stylesheet" type="text/css"/>
        <link href="css/Semantic-UI-Alert.css" rel="stylesheet" type="text/css"/>
    </head>

    <body ng-app="myApp" style="height: 100vh; overflow-y: hidden; ">

        <div class="ui grid">
            <div class="noir" style="width: 5vw; height: 110vh; margin: 0px!important; padding: 0px;important">
                <div style="padding-top: 5vw;">
                    <div id="bouton_profil" onclick="gestionnaireSections('#section_profil', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="user outline big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div id="bouton_pointeuse" onclick="gestionnaireSections('#section_pointeuse', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="stopwatch big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div id="bouton_accueil" onclick="gestionnaireSections('#section_accueil', this)" class="section_bouton active">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="home big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div id="bouton_stats" onclick="gestionnaireSections('#section_stats', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="chart pie big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div id="bouton_visites" onclick="gestionnaireSections('#section_visites', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="street view big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>

                    <div id="bouton_params" onclick="gestionnaireSections('#section_params', this)"  class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="whmcs big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>

                </div>
            </div>

            <div class="gris" style="width: 20vw; height: 110vh; padding-top: 25px;">
                <div style="padding-left: 10px;" id="section_accueil" class="section_muanza">
                    <h1>Accueil</h1>
                    <p>Cette section regroupe les informations de la journée</p>

                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item">
                            <a class="title">
                                <b>Tableau de bord</b>
                            </a>
                        </div>
                        <div class="item active">
                            <a class="active title">
                                <i class="dropdown icon"></i> <b>Discpline</b>
                            </a>
                            <div class="active content menu">
                                <a class="item" href="#!pointage">Abscences</a>
                                <a class="item" href="#!pointage">Retards</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Arrivées</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!pointage">Elèves</a>
                                <a class="item" href="#!pointage">Enseignants</a>
                                <a class="item" href="#!pointage">Collaborateurs</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_pointeuse" class="section_muanza">
                    <h1>Pointeuse</h1>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item">
                            <a class="title">
                                <b>Communication</b>
                            </a>
                            <div class="active content menu">
                                <a class="item active" href="#!tempsreel">Suivre en temps réel </a>
                                <a class="item active" href="#!tempsreel">Exporter les données </a>
                                <a class="item active" href="#!tempsreel">Configuration </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_profil"  class="section_muanza">
                    <h1>Mon profil</h1>

                </div>
                <div style="padding-left: 10px;" id="section_stats"  class="section_muanza">
                    <h1>Statistiques</h1>

                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item active">
                            <a class="active title">
                                <i class="dropdown icon"></i> 
                                <b>Globales</b>
                            </a>
                            <div class="active content menu">
                                <a class="item active" href="#!statistiques">Collaborateurs </a>
                                <a class="item active" href="#!statistiques">Enseignants </a>
                                <a class="item active" href="#!statistiques">Etudiants </a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Classements</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!statistiques">Moyenne d'arrivée par promotion</a>
                                <a class="item" href="#!statistiques">Etudiants toujours à l'heure </a>
                                <a class="item" href="#!statistiques">Les premiers à arriver sur le campus</a>
                                <a class="item" href="#!statistiques">Premiers arrivés par promotion</a>
                                <a class="item" href="#!statistiques">Derniers arrivés par promotion</a>
                                <a class="item" href="#!statistiques">Les plus retardataires par promotion</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_visites"  class="section_muanza">
                    <h1>Visites</h1>

                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item active">
                            <a class="active title">
                                <i class="dropdown icon"></i> <b>Actuellement dans nos locaux</b>
                            </a>
                            <div class="active content menu">
                                <a class="item active" href="#!entrees">Personne 1</a>
                                <a class="item" href="#!entrees">Personne 2</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Personnes attendues</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!visites">Personne 3</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Statistiques</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!statistiques">Par personne</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_params"  class="section_muanza">
                    <h1>Paramètres</h1>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item active">
                            <a class="title">
                                <i class="dropdown icon"></i> <b>Gestions des utilisateurs</b>
                            </a>
                            <div class="content menu">
                                <!-- Routage utilisateur OK-->
                                <a class="item active" href="#!utilisateurs">Utilisateurs</a>
                                <a class="item" href="#!profils">Profils utilisateurs</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Personnes et qualités</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!individus">Individus</a>
                                <a class="item" href="#!collaborateurs">Collaborateurs</a>
                                <a class="item" href="#!postes">Postes</a>
                                <a class="item" href="#!enseignants">Enseignants</a>
                                <a class="item" href="#!etudiants">Etudiants</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Paramètres académiques</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!anneeacademique">Années académiques</a>
                                <a class="item" href="#!filieres">Filières</a>
                                <a class="item" href="#!matieres">Matières</a>
                                <a class="item" href="#!coursall">Cours</a>
                                <a class="item" href="#!niveaux">Niveaux d'étude</a>
                                <a class="item" href="#!periodescreuses">Périodes creuses</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Accueil</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#!badges">Bagdes pour visiteur</a>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <div style="width: 75vw; height: 100vh; overflow-x: hidden; overflow-y: hidden; padding-top: 20px; padding-right: 0px!important;margin-right: 10px!important;">
                <div class="ui secondary  menu">
                    <a class="item teal">
                        <i class="circle loading icon titre"></i>
                        Pointeuse biométrique activée
                    </a>
                    <a id="chargement" class="item titre active ">
                        <i style="float:right" class="titre spinner loading icon"></i>
                        Chargement
                    </a>
                    <!--a class="item">
                        Exporter vers Excel
                    </a>
                    <a class="item">
                        Exporter en PDF
                    </a-->
                    <div class="right menu">
                        <div class="item">
                            <div class="ui icon input">
                                <input type="text" placeholder="Rechercher...">
                                <i class="search link icon"></i>
                            </div>
                        </div>
                        <a class="ui item">
                            ${empty sessionScope.utilisateur.individu.noms ? sessionScope.utilisateur.login:""}
                            ${sessionScope.utilisateur.individu.noms}
                            ${sessionScope.utilisateur.individu.prenoms}
                        </a>
                    </div>
                </div>
                <div style="height: 90vh; overflow-y: scroll">
                    ${notifications}
                    <div  ng-view style="min-height: 100vh; margin-top: -20px; padding-bottom: 40px;" class="espace_cotes">

                    </div>
                </div>
            </div>

        </div>




        <!-- Fichiers JS-->
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/semantic.js" type="text/javascript"></script>
        <script src="particles/particles.min.js" type="text/javascript"></script>
        <script src="js/Semantic-UI-Alert.js" type="text/javascript"></script>
        <script src="js/angular.min.js" type="text/javascript"></script>
        <script src="js/angular-route.js" type="text/javascript"></script>
        <script src="js/myapp.js" type="text/javascript"></script>
        <script src="js/routage.js" type="text/javascript"></script>
        <script>
                        $(document).ready(function () {
                            $.uiAlert({
                                textHead: 'Muanza',
                                text: 'est très beau',
                                bgcolor: '#004d6f',
                                textcolor: '#fff',
                                position: 'top-right', // top And bottom ||  left / center / right
                                icon: 'checkmark box',
                                time: 3
                            });

                        });
                        $(document).ajaxStart(function () {
                            console.log("AJAX started")
                        });

                        $(document).ajaxStop(function () {
                            console.log("AJAX stop")
                        });
                        $(window).bind('hashchange', function () {
                            $("#chargement").fadeIn("slow", function () {
                                $("#chargement").fadeOut("slow")
                            });
                        });
        </script>

    </body>
</html>
