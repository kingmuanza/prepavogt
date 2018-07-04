<%-- 
    Document   : visite
    Created on : 11 juin 2018, 12:52:14
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
                <c:when test="${empty visite}">Nouvelle visite</c:when>
                <c:otherwise>${visite.nomComplet}</c:otherwise>
            </c:choose>
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">${visite.individu.noms} ${visite.individu.prenoms}</a>
                                <div class="meta">
                                    <span class="date">
                                        ${empty visite.motif ? "Aucun motif":visite.motif}
                                    </span>
                                </div>
                                <div class="description">
                                    ${visite.commentaire}
                                </div>
                            </div>
                        </div>
                        <form class="ui form" action="VisiteServlet" method="post">

                            <input type="hidden" name="id" value="${visite.idvisite}"/>

                            <c:if test="${empty visite.entrees}">
                                <button class="ui fluid submit gris button" name="action" value="entree" type="submit" >
                                    La personne vient d'arriver
                                </button>
                            </c:if>

                        </form>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="VisiteServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez vos paramettre</li>
                                            <li>Les champs en (*) sont obligatoires !</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${visite.idvisite}"/>
                                <div class="field required">
                                    <label>Individu</label>
                                    <select class="ui dropdown" name="individu" required>
                                        <option>Aucun individu</option>
                                        <c:forEach items="${individus}" var="i">
                                            <option value="${i.idindividu}" ${visite.individu.idindividu==i.idindividu?"selected":""}>
                                                ${i.noms} ${i.prenoms}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Nom complet</label>
                                    <input type="text" name="nomComplet" value="${visite.nomComplet}" required>
                                </div>

                                <div class="field">
                                    <label>Motif</label>
                                    <input type="text" name="motif" value="${visite.motif}" required>
                                </div>

                                <div class="field">
                                    <label>Commentaire</label>
                                    <textarea name="commentaire" rows="2">${visite.commentaire}</textarea>
                                </div>

                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty visite}">
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
                ouvrirMenuCorrespondant("#section_visites", "bouton_visites", "visites");

            })
        </script>
    </body>
</html>

