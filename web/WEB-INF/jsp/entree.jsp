<%-- 
    Document   : entree
    Created on : 11 juin 2018, 12:47:09
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
                <c:when test="${empty visite}">Nouvelle Visite</c:when>
                <c:otherwise>Modifier / Supprimer une Visite</c:otherwise>
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
                                <a class="header">${visite.individu.noms} ${visite.individu.prenoms}</a>
                                <div class="meta">
                                    <span class="date">${visite.motif}</span>
                                </div>
                                <div class="description">
                                    ${visite.commentaire}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="EntreeServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez vos paramettre</li>
                                            <li>Les champs en (*) sont obligatoires !</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${entree.identree}"/>
                                <div class="field">
                                    <label>Individu</label>
                                    <select class="ui dropdown" name="indiv">
                                        <option>Aucun individu</option>
                                        <c:forEach items="${individus}" var="ind">
                                            <option value="${ind.idindividu}" ${visite.individu.idindividu==ind.idindividu?"selected":""}>
                                                ${ind.noms} ${ind.prenoms}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Badge</label>
                                    <select class="ui dropdown" name="badg">
                                        <option>Aucun badge</option>
                                        <c:forEach items="${badges}" var="badge">
                                            <option value="${badge.idbadge}" ${entree.badge.idbadge==badge.idbadge?"selected":""}>
                                                ${badge.code} : ${badge.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Visite</label>
                                    <select class="ui dropdown" name="visite">
                                        <option>Aucune visite</option>
                                        <c:forEach items="${visites}" var="visit">
                                            <option value="${visit.idvisite}" ${entree.visite.idvisite==visit.idvisite?"selected":""}>
                                                ${visit.motif}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Nom Complet</label>
                                    <input type="text" name="nomComplet" value="${entree.nomComplet}" required>
                                </div>
                                
                                <div class="field">
                                    <label>Motif</label>
                                    <input type="text" name="motif" value="${entree.motif}" required>
                                </div>
                                
                                <div class="field">
                                    <label>commentaire</label>
                                    <input type="text" name="commentaire" value="${entree.commentaire}" required>
                                </div>
                                <div class="field">
                                    <label>Date Entree</label><label>${entree.dateEntree}</label>
                                    <input type="date" name="dateEntree" value="" required>
                                </div>
                                <div class="field">
                                    <label>Date Sortie</label><label>${entree.dateSortie}</label>
                                    <input type="date" name="dateSortie" value="" required>
                                </div>
                                
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty entree}">
                                        <button class="ui submit red button" name="action" value="supprimer" type="submit">
                                            Supprimer
                                        </button>
                                    </c:if>
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
