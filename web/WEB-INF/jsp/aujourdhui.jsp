<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PREPA VOGT</title>

        <!-- Fichiers CSS pour le dataTable-->
        <link href="css/dataTables.semanticui.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/responsive.semanticui.min.css" rel="stylesheet" type="text/css"/>        
        <link href="css/buttons.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <h1 class="titre">
            Aujourd'hui
        </h1>
        <div style="padding-bottom: 20px; font-size: 1.2em; opacity: 0.8; margin-top: -15px">
            Dernière actualisation à 
            <span id="derniereHeure">
                <i class="spinner loading icon"></i>
            </span> 
        </div>

        <div  style="background-color: #f8f8f8; border-radius: 5px; border: 1px solid #004d6f">
            <div style="padding-top: 25px; padding-bottom : 25px">
                <div class="ui four statistics">
                    <div class="statistic">
                        <div class="value titre">
                            07:53
                        </div>
                        <div class="label">
                            Moyenne d'arrivée
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            <img src="img/joe.jpg" class="ui circular inline image">
                            MK
                        </div>
                        <div class="label">
                            Arrivée 06:35
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            42
                        </div>
                        <div class="label">
                            Retards
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            5
                        </div>
                        <div class="label">
                            Absences
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui three cards" style="padding-top: 20px;">
            
            <c:forEach items="${statistiques}" var="stat">
                <div class="ui card">
                    <div class="content">
                        <div class="ui black ribbon label" style="background-color: #004d6f!important">
                            ${stat.classe.niveauEtude.code}
                            ${stat.classe.filiere.libelle}
                        </div>
                        <div class="header"></div>
                        <div class="description">
                            <p></p>
                        </div>
                        <div class="meta">
                            <span class="right floated time">
                                ${stat.moyenneHeure}
                            </span>
                            <span class="category">Moyenne d'arrivée</span>
                        </div>
                        <div class="meta">
                            <span class="right floated time">${stat.retards.size()}</span>
                            <span class="category">Retards</span>
                        </div>
                        <div class="meta">
                            <span class="right floated time">${stat.absents.size()}</span>
                            <span class="category">Absents</span>
                        </div>
                        <div class="meta" style="opacity: 1!important; color: #777!important">
                            <span class="right floated time">
                                <b>${stat.classe.etudiants.size()}</b>
                            </span>
                            <span class="category"><b>Total</b></span>
                        </div>

                    </div>
                    <div class="extra content">
                        <div class="left floated author" style="opacity: 1!important; color: #777!important">
                            <i class="trophy icon"></i> 
                            ${premierArrivee.getNoms()}
                            ${premierArrivee.getPrenoms()}
                            07:25:36
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>


        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/jszip.js" type="text/javascript"></script>
        <script src="js/pdf.js.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script>
            var titre = 'Bonjour';
            var audio = new Audio('audio/sms-alert-3-daniel_simon.wav');

            var dernierIndex = 0;
            $(document).ready(function () {


            });
        </script>

    </body>
</html>
