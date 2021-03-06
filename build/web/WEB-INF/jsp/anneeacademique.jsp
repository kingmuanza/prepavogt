<%-- 
    Document   : newjspanneeacademique
    Created on : 11 juin 2018, 12:44:39
    Author     : zos hall
--%>

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
            ${empty anneeScolaire ? "Nouvelle année académique":anneeScolaire.libelle}
            
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                ${anneeScolaire.libelle}
                            </div>
                            <div class="extra content">
                                <div class="extra content">
                                    ${anneeScolaire.code}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="AnneeScolaireServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez code et les libellez</li>
                                        <li>Les dates ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="hidden" name="id" value="${anneeScolaire.idanneeScolaire}" readonly>
                                    <input type="text" name="code" value="${anneeScolaire.code}" required>
                                </div>
                                <div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${anneeScolaire.libelle}">
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Date Debut</label>
                                        <input type="text" name="dateDebut" value="${anneeScolaire.dateDebut}">
                                    </div>
                                    <div class="required field">
                                        <label>Date Fin</label>
                                        <input type="text" name="dateFin" value="${anneeScolaire.dateFin}">
                                    </div>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <button class="ui submit red button" name="action" value="supprimer" type="submit">
                                        Supprimer
                                    </button>
                                </div>

                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "anneeacademiques");

            })
        </script>
    </body>
</html>

