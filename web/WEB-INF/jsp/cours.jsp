 <%-- 
    Document   : cours
    Created on : 11 juin 2018, 12:46:21
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
                <c:when test="${empty cours}">Nouveau cours</c:when>
                <c:otherwise>
                    ${cours.matiere.libelle}
                    ${cours.niveauEtude.code} ${cours.filiere.libelle}
                </c:otherwise>
            </c:choose>

        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">
                                    ${cours.matiere.libelle}
                                </a>
                                <div class="description">
                                    ${cours.niveauEtude.code} ${cours.filiere.libelle}
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    ${cours.matiere.code}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="CoursServlet" method="post">
                                <div class="ui error message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez votre login</li>
                                        <li>Les mots de passe ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <input type="hidden" name="id" value="${cours.idcours}" required>
                                <div class="required field">
                                    <label>Matiere</label>
                                    <select class="ui dropdown" name="matiere">
                                        <option>Aucune matiere</option>
                                        <c:forEach items="${matieres}" var="m">
                                            <option value="${m.idmatiere}" ${cours.matiere.idmatiere==m.idmatiere?"selected":""}>
                                                ${m.code} - ${m.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="required field">
                                    <label>Filiere</label>
                                    <select class="ui dropdown" name="filiere">
                                        <option>Aucune filiere</option>
                                        <c:forEach items="${filieres}" var="f">
                                            <option value="${f.idfiliere}" ${cours.filiere.idfiliere==f.idfiliere?"selected":""}>
                                                ${f.code} - ${f.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="required field">
                                    <label>Niveau d'etude</label>
                                    <select class="ui dropdown" name="niveauEtude">
                                        <option>Aucune niveau</option>
                                        <c:forEach items="${niveauEtudes}" var="n">
                                            <option value="${n.idniveauEtude}" ${cours.niveauEtude.idniveauEtude==n.idniveauEtude?"selected":""}>
                                                ${n.code} - ${n.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty cours}">
                                        <div class="ui red button" id="supModal">
                                            Supprimer
                                        </div>
                                    </c:if>
                                </div>
                            </form>

                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="CoursServlet" method="post">
                                        <p>Veuillez confirmer la suppression ?</p>
                                        <input type="hidden" name="id" value="${cours.idcours}">
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

                            </form>
                        </div>

                    </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "coursall");

            })
        </script>
        <script>
            $("#supModal").click(function () {
                $('.ui.modal.basic').modal('show');
            });
        </script>
    </body>
</html>
