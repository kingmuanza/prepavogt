<%-- 
    Document   : collaborateur
    Created on : 11 juin 2018, 12:45:45
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
                <c:when test="${empty employe}">Nouveau collaborateur</c:when>
                <c:otherwise>
                    ${employe.individu.noms} ${employe.individu.prenoms} 
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
                                <a class="header">
                                    ${employe.poste.libelle}
                                </a>
                                <div class="meta">
                                    <span class="date"></span>
                                </div>
                                <div class="description">
                                    ${employe.poste.code}
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    <i class="user icon"></i>
                                    ${employe.individu.noms} ${employe.individu.prenoms}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="EmployeServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez votre login</li>
                                            <li>Les mots de passe ne sont pas identiques</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${employe.idemploye}"/>
                                <div class="field required">
                                    <label>Poste</label>
                                    <select class="ui dropdown" name="poste" required>
                                        <option>Aucun poste</option>
                                        <c:forEach items="${postes}" var="p">
                                            <option value="${p.idposte}" ${employe.poste.idposte==p.idposte?"selected":""}>
                                                ${p.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field required">
                                    <label>Individu
                                        <span id="new_individu" style="float: right; cursor: pointer">
                                            <i class="plus icon"/>
                                        </span>
                                    </label>

                                    <select class="ui dropdown" name="individu" required>
                                        <option>Aucun individu</option>
                                        <c:forEach items="${individus}" var="i">
                                            <option value="${i.idindividu}" 
                                                    <c:choose>
                                                        <c:when test="${employe.individu.idindividu==i.idindividu}">selected</c:when>
                                                        <c:when test="${requestScope.nouvelIndividu.idindividu==i.idindividu}">selected</c:when>
                                                    </c:choose>
                                                    >
                                                ${i.noms} ${i.prenoms}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty employe}">
                                        <button class="ui submit red button" name="action" value="supprimer" type="submit">
                                            Supprimer
                                        </button>
                                    </c:if>
                                </div>

                            </form>
                        </div>
                        <!-- MODAL  -->
                        <div class="ui modal">
                            <i class="close icon"></i>
                            <div class="header">
                                Nouvel individu
                            </div>
                            <div class="image content">
                                <div class="ui medium image" style="cursor: pointer">
                                    <img src="img/joe.jpg">
                                </div>
                                <div class="description">
                                    <div class="ui header">Choisir une photo de profil.</div>

                                </div>
                            </div>
                            <div style="padding: 30px">
                                <form class="ui form" action="EmployeServlet" method="post">
                                    <div class="content">
                                        <input type="hidden" name="newIndividu" value="collaborateur/${employe.individu.idindividu}"/>
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
                                    </div>
                                    <div class="actions">
                                        <div class="ui black deny button">
                                            Annuler
                                        </div>
                                        <button  type="submit" class="ui positive right labeled icon button">
                                            Enregistrer
                                            <i class="checkmark icon"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <!-- MODAL  -->
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
            ouvrirMenuCorrespondant("#section_params", "bouton_params", "collaborateurs");
            $("#new_individu").click(function () {
                $('.ui.modal').modal('show');
            });
            $("#ajax_submit").click(function () {
                $.ajax({
                type: "POST",
                        url: "EmployeServlet",
                        data: {
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
                        },
                        success: function(data) {
                            
                        },
                        error:  function(xhr, str){
                            
                        }
                }).done(function() {
                        
                    });
                });
            });
            
            
        </script>
    </body>
</html>

