<%-- 
    Document   : periodecreuse
    Created on : 11 juin 2018, 12:49:47
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
            Nouvel periode creuse
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">${periodeCreuse.libelle}</a>
                                <div class="meta">
                                    <span class="date">${periodeCreuse.code}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="PeriodeCreuseServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez votre login</li>
                                        <li>Les mots de passe ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="hidden" name="id" value="${periodeCreuse.idperiodeCreuse}">
                                    <input type="text" name="code" value="${periodeCreuse.code}" required>
                                </div><div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${periodeCreuse.libelle}" required>
                                </div>
                                <div class="required field">
                                    <label>Date Debut :
                                        <label style="color: navy;">${periodeCreuse.dateDebut}</label>
                                    </label>

                                    <input type="date" name="dateDebut" value="${periodeCreuse.dateDebut}" required>
                                </div>
                                <div class="required field">
                                    <label>Date Fin
                                        <label style="color: navy;">${periodeCreuse.dateFin}</label>
                                    </label>

                                    <input type="date" name="dateFin" value="${periodeCreuse.dateFin}" required>
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "utilisateurs");

            })
        </script>
    </body>
</html>
