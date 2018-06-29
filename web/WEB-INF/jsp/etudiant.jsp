<%-- 
    Document   : etudiant
    Created on : 11 juin 2018, 12:48:04
    Author     : zos hall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <c:when test="${empty etudiant}">Nouvel Etudiant</c:when>
                <c:otherwise>
                    ${etudiant.individu.noms} ${etudiant.individu.prenoms}
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
                                <a class="header">${etudiant.individu.noms} ${etudiant.individu.prenoms}</a>
                                <div class="meta">
                                    Né le ${etudiant.individu.datenaiss}
                                    à ${etudiant.individu.lieunaiss}
                                </div>
                                <div class="description">
                                    <span class="date">
                                        ${etudiant.classe.niveauEtude.code} 
                                        ${etudiant.classe.filiere.libelle}
                                    </span>
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    ${etudiant.classe.niveauEtude.code}
                                    ${etudiant.classe.filiere.libelle}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="EtudiantServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez votre login</li>
                                            <li>Les mots de passe ne sont pas identiques</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${etudiant.idetudiant}"/>
                                <div class="field">
                                    <label>Année Academique</label>
                                    <select class="ui dropdown" name="anneeSco">
                                        <option>Aucune année</option>
                                        <c:forEach items="${anneeScolaires}" var="annee">
                                            <option value="${annee.idanneeScolaire}" ${etudiant.anneeScolaire.idanneeScolaire==annee.idanneeScolaire?"selected":""}>
                                                ${annee.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Classe</label>
                                    <select class="ui dropdown" name="filiere">
                                        <option>Aucune classe</option>
                                        <c:forEach items="${classes}" var="c">
                                            <option value="${c.idclasse}" ${etudiant.classe.idclasse==c.idclasse?"selected":""}>
                                                ${c.niveauEtude.code} ${c.filiere.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="field">
                                    <label>Individu
                                        <span id="new_individu" style="float: right; cursor: pointer">
                                            <i class="plus icon"/>
                                        </span>
                                    </label>
                                    <select class="ui dropdown" name="individu">
                                        <option>Aucun individu</option>
                                        <c:forEach items="${individus}" var="i">
                                            <option value="${i.idindividu}" ${etudiant.individu.idindividu==i.idindividu?"selected":""}>
                                                ${i.noms} ${i.prenoms}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>

                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty etudiant}">
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
                                <form style="width: 100%" class="ui form description" action="EtudiantServlet" method="post">
                                    <input type="hidden" name="newIndividu" value="etudiant/${etudiant.individu.idindividu}"/>
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
