<%-- 
    Document   : matiere
    Created on : 11 juin 2018, 12:49:01
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
            <c:choose>
                <c:when test="${empty matiere}">Nouvelle matière</c:when>
                <c:otherwise>${matiere.libelle}</c:otherwise>
            </c:choose>
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            
                            <div class="content">
                                <a class="header">${matiere.libelle}</a>
                            </div>
                            <div class="extra content">
                                <a>
                                    ${matiere.code}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="MatiereServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez votre login</li>
                                        <li>Les mots de passe ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <input type="hidden" name="id" value="${matiere.idmatiere}">
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="text" name="code" value="${matiere.code}" required>
                                </div>
                                <div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${matiere.libelle}" required>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty matiere}">
                                        <div class="ui red button" id="supModal">
                                            Supprimer
                                        </div>
                                    </c:if>
                                </div>
                            </form>
                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="MatiereServlet" method="post">
                                        <p>Veuillez confirmer la suppression ?</p>
                                        <input type="hidden" name="id" value="${matiere.idmatiere}">
                                        <div >
                                            <button class="ui submit gris button" >
                                                Annuler
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
            </div>
        </div>
        <script>
            $(document).ready(function () {
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "matieres");

            })
        </script>
        <script>
            $("#supModal").click(function () {
                $('.ui.modal.basic').modal('show');
            });
        </script>
    </body>
</html>
