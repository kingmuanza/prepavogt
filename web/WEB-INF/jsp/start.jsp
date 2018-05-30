<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PREPA VOGT</title>
        <!-- Fichiers CSS-->
        <link href="css/semantic.css" rel="stylesheet" type="text/css"/>
        <!-- Fichiers JS-->
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/semantic.js" type="text/javascript"></script>
        <script language='javascript'>
            $(document).ready(function () {
                $('.ui.accordion').accordion();

                $('.section_muanza').hide();

                $('#section_accueil').show();


            });

            function gestionnaireSections(id, elem) {
                //id de la section à ouvrir
                $('.section_muanza').hide();
                $('.section_bouton').removeClass("active");
                console.log(elem);
                elem.classList.add("active");

                $(id).show();

            }
        </script>
    </head>
    <style>
        .gris{
            background-color: #004d6f;
            color: white!important;
        }

        .rond{
            border-radius: 50%;
            border: 4px solid #00B5AD;
        }

        .moyen{
            height: 100px!important;
            width: 100px!important;
        }

        .fg-white{
            color:white!important;
        }

        .espace_haut{
            padding-top: 10px;
        }

        .noir{
            background-color: #111;
            color:white;
        }

        .title{
            color:#fff!important;
        }
        .active.title{
            color:#fff!important;
        }

        .gris .item{
            color:#bbb!important; 
        }

        .section_bouton{
            padding: 5px; 
            width: 5vw; 
            height: 4vw; 
            color: white!important;
            cursor: pointer;
        }
        .section_bouton.active{
            background-color: #004d6f;
        }
        
        .section_muanza h1{
            color: #efefef!important;
        }


    </style>

    <body style="height: 100vh; overflow-y: hidden; ">

        <div class="ui grid">
            <div class="noir" style="width: 5vw; height: 110vh; margin: 0px!important; padding: 0px;important">
                <div style="padding-top: 5vw;">
                    <div onclick="gestionnaireSections('#section_profil', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="user outline big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div onclick="gestionnaireSections('#section_pointeuse', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="stopwatch big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div onclick="gestionnaireSections('#section_accueil', this)" class="section_bouton active">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="home big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div onclick="gestionnaireSections('#section_stats', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="chart pie big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>
                    <div onclick="gestionnaireSections('#section_visites', this)" class="section_bouton">
                        <div style="position: relative;top: 50%; left: 30%;transform: translateY(-50%);">
                            <i class="street view big icon fg-white" style="z-index: 45177777;"></i>
                        </div>
                    </div>

                    <div onclick="gestionnaireSections('#section_params', this)"  class="section_bouton">
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
                                <a class="item active" href="#menu">Abscences</a>
                                <a class="item" href="#secondary-menu">Retards</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Arrivées</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Elèves</a>
                                <a class="item" href="#sub-menu">Enseignants</a>
                                <a class="item" href="#sub-menu">Collaborateurs</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_pointeuse" class="section_muanza">
                    <h1>Pointeuse</h1>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item">
                            <a class="title">
                                <b>Temps réel</b>
                            </a>
                        </div>
                    </div>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item">
                            <a class="title">
                                <b>Historique</b>
                            </a>
                        </div>
                    </div>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item">
                            <a class="title">
                                <b>Régularisation</b>
                            </a>
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
                                <a class="item active" href="#menu">Collaborateurs </a>
                                <a class="item active" href="#menu">Enseignants </a>
                                <a class="item active" href="#menu">Etudiants </a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Classements</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Moyenne d'arrivée par promotion</a>
                                <a class="item active" href="#menu">Etudiants toujours à l'heure </a>
                                <a class="item" href="#secondary-menu">Les premiers à arriver sur le campus</a>
                                <a class="item" href="#sub-menu">Premiers arrivés par promotion</a>
                                <a class="item" href="#sub-menu">Derniers arrivés par promotion</a>
                                <a class="item" href="#sub-menu">Les plus retardataires par promotion</a>
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
                                <a class="item active" href="#menu">Personne 1</a>
                                <a class="item" href="#secondary-menu">Personne 2</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Personnes attendues</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Personne 3</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Statistiques</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Par personne</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="padding-left: 10px;" id="section_params"  class="section_muanza">
                    <h1>Paramètres</h1>
                    <div class="ui accordion vertical fluid following text menu">
                        <div class="item active">
                            <a class="active title">
                                <i class="dropdown icon"></i> <b>Gestions des utilisateurs</b>
                            </a>
                            <div class="active content menu">
                                <a class="item active" href="#menu">Utilisateurs</a>
                                <a class="item" href="#secondary-menu">Profils utilisateurs</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Personnes et qualités</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Individus</a>
                                <a class="item" href="#sub-menu">Collaborateurs</a>
                                <a class="item" href="#sub-menu">Postes</a>
                                <a class="item" href="#sub-menu">Enseignants</a>
                                <a class="item" href="#sub-menu">Elèves</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Paramètres académiques</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Années académiques</a>
                                <a class="item" href="#sub-menu">Filières</a>
                                <a class="item" href="#sub-menu">Matières</a>
                                <a class="item" href="#sub-menu">Cours</a>
                                <a class="item" href="#sub-menu">Niveaux d'étude</a>
                                <a class="item" href="#sub-menu">Périodes creuses</a>
                            </div>
                        </div>
                        <div class="item">
                            <a class="title">
                                <i class="dropdown icon"></i> 
                                <b>Accueil</b>
                            </a>
                            <div class="content menu">
                                <a class="item" href="#sub-menu">Bagdes pour visiteur</a>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
            <div style="width: 75vw; height: 100vh; overflow-x: hidden; overflow-y: hidden; padding-top: 20px; padding-right: 0px!important;margin-right: 10px!important;">
                <div class="ui secondary  menu">
                    <a class="item">
                        Imprimer
                    </a>
                    <a class="item">
                        Exporter vers Excel
                    </a>
                    <a class="item">
                        Exporter en PDF
                    </a>
                    <div class="right menu">
                        <div class="item">
                            <div class="ui icon input">
                                <input type="text" placeholder="Rechercher...">
                                <i class="search link icon"></i>
                            </div>
                        </div>
                        <a class="ui item">
                            Déconnexion
                        </a>
                    </div>
                </div>
                <div style="height: 90vh; overflow-y: scroll">
                    <div style="height: 450vh;">

                    </div>
                </div>
            </div>

        </div>


    </body>
</html>
