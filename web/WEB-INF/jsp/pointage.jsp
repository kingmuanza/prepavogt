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
            Nouveau pointage / Modifier / Supprimer
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="image">
                                <img src="img/joe.jpg">
                            </div>
                            <div class="content">
                                <a class="header">${pointage.numero}</a>
                                <div class="meta">
                                    <span class="date">${pointage.machine}</span>
                                </div>
                                <div class="description">
                                    ${pointage.heure}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="PointageServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez votre login</li>
                                        <li>Les mots de passe ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Numero</label>
                                    <input type="text" name="numero" value="${pointage.numero}" required>
                                </div>
                                <div class="required field">
                                    <label>Matricule</label>
                                    <input type="text" name="matricule" value="${pointage.matricule}" required>
                                </div>
                                <div class="required field">
                                    <label>Machine</label>
                                    <input type="text" name="machine" value="${pointage.machine}" required>
                                </div>
                                <div class="required field">
                                    <label>Mode</label>
                                    <input type="text" name="mode" value="${pointage.mode}" required>
                                </div>
                                <div class="required field">
                                    <label>I/O MD</label>
                                    <input type="text" name="iomd" value="${pointage.iomd}" required>
                                </div>
                                <div class="required field">
                                    <label>Heure</label>
                                    <input type="datetime" name="heure" value="${pointage.heure}" required>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty pointage}">
                                        <div class="ui red button" id="supModal">
                                            Supprimer
                                        </div>
                                    </c:if>
                                </div>
                            </form>
                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="PointageServlet" method="post">
                                        <p>Veuillez confirmer la suppression ?</p>
                                        <input type="hidden" name="id" value="${pointage.idpointage}">
                                        <div >
                                            <button class="ui black deny button" >
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
            <script>
                $(document).ready(function () {
                    ouvrirMenuCorrespondant("#section_pointeuse", "bouton_pointeuse", "pointages");

                })
            </script>
            <script>
                $("#supModal").click(function () {
                    $('.ui.modal.basic').modal('show');
                });
            </script>
    </body>
</html>
