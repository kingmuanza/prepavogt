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
                    ${cours.classe.niveauEtude.code} ${cours.classe.filiere.libelle}
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
                                    ${cours.classe.niveauEtude.code} ${cours.classe.filiere.libelle}
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
                                    <label>Matière</label>
                                    <select class="ui dropdown" name="matiere">
                                        <option>Aucune matiere</option>
                                        <c:forEach items="${matieres}" var="m">
                                            <option value="${m.idmatiere}" ${cours.matiere.idmatiere==m.idmatiere?"selected":""}>
                                                ${m.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Classe</label>
                                    <select class="ui dropdown" name="classe">
                                        <option>Aucune classe</option>
                                        <c:forEach items="${classes}" var="c">
                                            <option value="${c.idclasse}" ${cours.classe.idclasse==c.idclasse?"selected":""}>
                                                ${c.niveauEtude.code} ${c.filiere.libelle}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty cours}">
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "coursall");

            })
        </script>
    </body>
</html>
