<%-- 
    Document   : enseignant
    Created on : 11 juin 2018, 12:46:47
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
                <c:when test="${empty enseignant}">Nouvel enseignant</c:when>
                <c:otherwise>
                    ${enseignant.individu.noms} ${enseignant.individu.prenoms}
                </c:otherwise>
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
                                <a class="header">${enseignant.individu.noms} ${enseignant.individu.prenoms}</a>
                                <div class="meta">
                                    <span class="date">${enseignant.individu.datenaiss}</span>
                                </div>
                                <div class="description">
                                    <c:forEach items="${enseignant.coursEnseignants}" var="courEn">
                                        <span>
                                            <b>${courEn.cours.matiere.libelle}&nbsp;</b>
                                            ${courEn.cours.niveauEtude.code} ${courEn.cours.filiere.libelle}&nbsp;
                                        </span>
                                        <br>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    <i class="user icon"></i>
                                    ${enseignant.individu.tel1} 
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="EnseignantServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez votre login</li>
                                            <li>Les mots de passe ne sont pas identiques</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${enseignant.idenseignant}"/>

                                <div class="field required">
                                    <label>Individu
                                        <span id="new_individu" style="float: right; cursor: pointer">
                                            <i class="plus icon"/>
                                        </span>
                                    </label>
                                    <select class="ui dropdown" name="individu" required>
                                        <option>Aucun individu</option>
                                        <c:forEach items="${individus}" var="i">
                                            <option value="${i.idindividu}" ${enseignant.individu.idindividu==i.idindividu?"selected":""}>
                                                ${i.noms} ${i.prenoms}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>

                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty enseignant}">
                                        <button class="ui submit red button" name="action" value="supprimer" type="submit">
                                            Supprimer
                                        </button>
                                    </c:if>
                                </div>

                            </form>
                        </div>

                        <!-- MODAL  -->
                        <div class="ui modal">
                            <div class="header titre">
                                Nouvel individu
                            </div>
                            <div class="image content">
                                <div class="ui medium image" style="cursor: pointer">
                                    <img src="img/joe.jpg" style="width: 100%;">
                                </div>
                                <form style="width: 100%" class="ui form description" action="EnseignantServlet" method="post">
                                    <input type="hidden" name="newIndividu" value="enseignant/${enseignant.individu.idindividu}"/>
                                    <div class="required field">
                                        <label>Matricule</label>
                                        <input type="text" name="matricule" value="" required>
                                    </div>
                                    <div class="two fields">
                                        <div class="required field">
                                            <label>Civilité</label>
                                            <input type="text" name="civilite" value="" required>
                                        </div>
                                        <div class="required field">
                                            <label>Genre</label>
                                            <select class="ui dropdown" name="genre">
                                                <option value="true" >Femme</option>
                                                <option value="false" >Homme</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="two fields">
                                        <div class="required field">
                                            <label>Noms</label>
                                            <input type="text" name="noms" value="" required>
                                        </div>
                                        <div class="required field">
                                            <label>Prénoms</label>
                                            <input type="text" name="prenoms" value="" required>
                                        </div>
                                    </div>
                                    <div class="two fields">
                                        <div class="required field">
                                            <label>Date de naissance</label>
                                            <input type="date" name="dateNaissance" value="" required>
                                        </div>
                                        <div class="required field">
                                            <label>Lieu de naissance</label>
                                            <input type="text" name="lieuNaissance" value="" required>
                                        </div>
                                    </div>
                                    <div class="two fields">
                                        <div class=" field">
                                            <label>Résidence</label>
                                            <input type="text" name="residence" value="">
                                        </div>
                                        <div class=" field">
                                            <label>Adresse mail</label>
                                            <input type="text" name="email" value="">
                                        </div>
                                    </div>
                                    <div class="two fields">
                                        <div class="required field">
                                            <label>Télephone 1</label>
                                            <input type="text" name="telephone1" value="" required>
                                        </div>
                                        <div class="field">
                                            <label>Télephone 2</label>
                                            <input type="text" name="telephone2" value="">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="actions">
                                <div class="ui black deny button">
                                    Annuler
                                </div>
                                <button id="ajax_submit" class="ui submit gris right labeled icon button">
                                    Enregistrer
                                    <i class="checkmark icon"></i>
                                </button>
                            </div>
                        </div>
                        <!-- MODAL  -->

                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "enseignants");
                $("#new_individu").click(function () {
                    $('.ui.modal').modal('show');
                });
                $("#ajax_submit").click(function () {
                    var data = {
                        newIndividu: $("[name='newIndividu']").val(),
                        matricule: $("[name='matricule']").val(),
                        civilite: $("[name='civilite']").val(),
                        genre: $("[name='genre']").val(),
                        noms: $("[name='noms']").val(),
                        prenoms: $("[name='prenoms']").val(),
                        dateNaissance: $("[name='dateNaissance']").val(),
                        lieuNaissance: $("[name='lieuNaissance']").val(),
                        residence: $("[name='residence']").val(),
                        email: $("[name='email']").val(),
                        telephone1: $("[name='telephone1']").val(),
                        telephone2: $("[name='telephone2']").val()
                    };
                    console.log(data);
                    $.ajax({
                        type: "POST",
                        url: "EtudiantServlet",
                        data: data,
                        success: function (data) {
                            $('.ui.modal').modal('hide');
                            console.log(data);
                            var donnees = JSON.parse(data);
                            $("[name='individu']").html("<option value='" + donnees.id + "'>" + donnees.noms + " " + donnees.prenoms + "</option>");
                        },
                        error: function (xhr, str) {
                        }
                    }).done(function () {
                        console.log("Fin de la fonction");
                    });
                });

            });
        </script>
    </body>
</html>
