<%-- 
    Document   : individu
    Created on : 11 juin 2018, 12:48:33
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
                <c:when test="${empty individu}">Nouvel individu</c:when>
                <c:otherwise>${individu.prenoms} ${individu.noms}</c:otherwise>
            </c:choose>
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
                                <a class="header">${individu.prenoms} ${individu.noms}</a>

                                <div class="meta">
                                    <span class="date">
                                        <c:if test="${!empty individu}">
                                            Né le ${individu.datenaiss}
                                            à ${individu.lieunaiss}
                                        </c:if>
                                    </span>
                                    </div>
                                    <div class="description">
                                    <c:if test="${!empty individu}">Réside à ${individu.residence}</c:if>
                                    </div>
                                </div>
                                <div class="extra content">
                                    <a>
                                        <i class="user icon"></i>
                                    ${individu.tel1}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="IndividuServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez votre login</li>
                                            <li>Les mots de passe ne sont pas identiques</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${individu.idindividu}"/>
                                <div class="required field">
                                    <label>Matricule</label>
                                    <input type="text" name="matricule" value="${individu.matricule}" required>
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Civilité</label>
                                        <input type="text" name="civilite" value="${individu.civilite}" required>
                                    </div>
                                    <div class="required field">
                                        <label>Genre</label>
                                        <select class="ui dropdown" name="genre">
                                            <option value="true" ${individu.genre?"selected":""}>Femme</option>
                                            <option value="false" ${!individu.genre?"selected":""}>Homme</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Noms</label>
                                        <input type="text" name="noms" value="${individu.noms}" required>
                                    </div>
                                    <div class="required field">
                                        <label>Prénoms</label>
                                        <input type="text" name="prenoms" value="${individu.prenoms}" required>
                                    </div>
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Date de naissance</label>
                                        <input type="date" name="dateNaissance" value="${individu.datenaiss}" required>
                                    </div>
                                    <div class="required field">
                                        <label>Lieu de naissance</label>
                                        <input type="text" name="lieuNaissance" value="${individu.lieunaiss}" required>
                                    </div>
                                </div>
                                <div class="two fields">
                                    <div class=" field">
                                        <label>Résidence</label>
                                        <input type="text" name="residence" value="${individu.residence}">
                                    </div>
                                    <div class=" field">
                                        <label>Adresse mail</label>
                                        <input type="text" name="email" value="${individu.email}">
                                    </div>
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Télephone 1</label>
                                        <input type="text" name="telephone1" value="${individu.tel1}" required>
                                    </div>
                                    <div class="field">
                                        <label>Télephone 2</label>
                                        <input type="text" name="telephone2" value="${individu.tel2}">
                                    </div>
                                </div>

                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty individu}">
                                        <div class="ui red button" id="supModal">
                                            Supprimer
                                        </div>
                                    </c:if>
                                </div>
                            </form>
                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="IndividuServlet" method="post">
                                        <p>Veuillez confirmer la suppression ?</p>
                                        <input type="hidden" name="id" value="${individu.idindividu}"/>
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "individus");

            })
        </script>
        <script>
            $("#supModal").click(function () {
                $('.ui.modal.basic').modal('show');
            });
        </script>
    </body>
</html>
