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
            Statistiques
        </h1>

        <div  style="background-color: #f8f8f8; border-radius: 5px; border: 0px solid #004d6f">
            <div style="padding-top: 25px; padding-bottom : 25px">


                <div class="ui four statistics">
                    <div class="statistic">
                        <div class="value titre">
                            07:49
                        </div>
                        <div class="label">
                            Moyenne d'arrivée
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="text value  titre">
                            2A<br>
                            Ingénieur
                        </div>
                        <div class="label">
                            Ponctualité
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            <i class="clock outline icon"></i>
                        </div>
                        <div class="label">
                            06:35
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            <img src="img/joe.jpg" class="ui circular inline image">
                            42
                        </div>
                        <div class="label">
                            Toujours ponctuels
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <div style="padding-top: 25px; padding-bottom : 25px">
                <div class="ui three column grid">
                    <div class="column">
                        <div class="ui fluid card">
                            <div class="image">
                                <img src="img/trophy.png">
                            </div>
                            <div class="content">
                                <a class="header">
                                    Moyenne d'arrivée par promotion
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="ui fluid card">
                            <div class="image">
                                <img src="img/medal.png">
                            </div>
                            <div class="content">
                                <a class="header">Premier arrivé par promotion</a>
                            </div>
                        </div>
                    </div>
                    <div class="column">
                        <div class="ui fluid card">
                            <div class="image">
                                <img src="img/first.png">
                            </div>
                            <div class="content">
                                <a class="header">Elliot Fu</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>




        <!-- Fichiers js pour le dataTable-->
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/dataTables.semanticui.min.js" type="text/javascript"></script>
        <script src="js/dataTables.responsive.min.js" type="text/javascript"></script>
        <script src="js/responsive.semanticui.min.js" type="text/javascript"></script>

        <!-- Datatable utilisateur -->
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/pdf.js.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>

    </body>
</html>
