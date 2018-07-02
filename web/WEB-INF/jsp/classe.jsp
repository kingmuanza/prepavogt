<%-- 
    Document   : classe
    Created on : 29 juin 2018, 11:53:00
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
                <c:when test="${empty classe}">Nouvelle Classe</c:when>
                <c:otherwise>Classe ${classe.idclasse}</c:otherwise>
            </c:choose>
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">${classe.niveauEtude.code}</a>
                                <div class="meta">
                                    <span class="date">${classe.filiere.libelle}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="ClasseServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez vos paramettres</li>
                                        <li>Les champs contenant les (*) sont obligatoire</li>
                                    </ul>
                                </div>
                                <div class="field">
                                    <label>Niveau</label>
                                    <select class="ui dropdown" name="niveau">
                                        <option>Aucun niveau</option>
                                        <c:forEach items="${niveaux}" var="niveau">
                                            <option value="${niveau.idniveauEtude}" ${classe.niveauEtude.idniveauEtude==niveau.idniveauEtude?"selected":""}>
                                                ${niveau.code} ${niveau.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Filiere</label>
                                    <select class="ui dropdown" name="filiere">
                                        <option>Aucune Filiere</option>
                                        <c:forEach items="${filieres}" var="filier">
                                            <option value="${filier.idfiliere}" ${classe.filiere.idfiliere==filier.idfiliere?"selected":""}>
                                                ${filier.code} ${filier.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty classe}">
                                        <div class="ui red button" id="supModal">
                                            Supprimer
                                        </div>
                                    </c:if>
                                </div>
                            </form>

                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="ClasseServlet" method="post">
                                        <p>Veuillez confirmer la suppression ?</p>
                                        <input type="hidden" name="id" value="${classe.idclasse}">
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "classes");

            })
        </script>
        <script>
            $("#supModal").click(function () {
                $('.ui.modal.basic').modal('show');
            });
        </script>
    </body>
</html>
