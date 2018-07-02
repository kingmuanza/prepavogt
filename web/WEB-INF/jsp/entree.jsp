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
                <c:when test="${empty visite}">Nouvelle Entree</c:when>
                <c:otherwise>Modifier / Supprimer une Entree</c:otherwise>
            </c:choose>
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">${entree.motif}</a>
                                <div class="description">
                                    ${entree.commentaire}
                                </div>
                            </div>
                            <div class="content">
                                ${entree.dateEntree}
                                <div class="sub header">
                                    ${entree.dateSortie}
                                </div>
                            </div>
                        </div>
                        <h4 class="ui image header">
                            <form class="ui form" action="EntreeServlet" method="post">

                                <input type="hidden" name="id" value="${entree.identree}"/>

                                <div class="ui fluid card">
                                    <c:if test="${empty entree.dateSortie}">
                                        <button class="ui fluid submit gris button" name="action" value="sortir" type="submit" >
                                            Sortir
                                        </button>
                                    </c:if>
                                </div>
                                
                            </form>
                        </h4>
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
                                <div class="fields">
                                    <div class="twelve wide field">
                                        <label>Nom complet</label>
                                        <input type="text" name="nomComplet" value="${entree.nomComplet}" required>
                                    </div>
                                    <div class="four wide field">
                                        <label>Badge</label>
                                        <select class="ui dropdown" name="badg">
                                            <option>Aucun badge</option>
                                            <c:forEach items="${badges}" var="badge">
                                                <option value="${badge.idbadge}" ${entree.badge.idbadge==badge.idbadge?"selected":""}>
                                                    ${badge.libelle}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>


                                <div class="field">
                                    <label>Motif</label>
                                    <input type="text" name="motif" value="${entree.motif}" required>
                                </div>

                                <div class="field">
                                    <label>Commentaire</label>
                                    <textarea name="commentaire" rows="2">
                                        ${entree.commentaire}
                                    </textarea>
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
