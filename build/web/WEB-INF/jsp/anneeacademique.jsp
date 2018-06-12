<%-- 
    Document   : newjspanneeacademique
    Created on : 11 juin 2018, 12:44:39
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
            Nouvel annee academique
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="AnneeScolaireServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez code et les libellez</li>
                                        <li>Les dates ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>ID</label>
                                    <input type="text" name="id" value="${anneeScolaire.idanneeScolaire}" readonly required>
                                </div>
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="text" name="code" value="${anneeScolaire.code}" required>
                                </div>
                                <div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${anneeScolaire.libelle}">
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Date Debut</label>
                                        <input type="text" name="dateDebut" value="${anneeScolaire.dateDebut}">
                                    </div>
                                    <div class="required field">
                                        <label>Date Fin</label>
                                        <input type="text" name="dateFin" value="${anneeScolaire.dateFin}">
                                    </div>
                                </div>
                                <div class="field">
                                    <label>Etudiant</label>
                                    <select class="ui dropdown" name="individu">
                                        <option>Aucune personne</option>
                                        <c:forEach items="${etudiants}" var="e">
                                            <option value="${e.idetudiant}" >
                                                ${e.individu.noms} ${e.individu.prenoms}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        enregistrer
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
        <script>
            $(document).ready(function () {
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "utilisateurs");

            })
        </script>
    </body>
</html>

