<%-- 
    Document   : profil
    Created on : 11 juin 2018, 12:51:39
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
            Nouveau Profil utilisateur
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="six wide column">
                        <div class="ui fluid card">
                            <div class="content">
                                <a class="header">${utilisateurProfil.code}</a>
                                <div class="meta">
                                    <span class="date">${utilisateurProfil.libelle}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="UtilisateurServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez vos nouvelles paramettres</li>
                                        <li>Les paramettres renseignées ne sont pas conformes</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="hidden" name="code" value="${utilisateurProfil.idutilisateurProfil}">
                                    <input type="text" name="code" value="${utilisateurProfil.code}" required>
                                </div>
                                <div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${utilisateurProfil.libelle}">
                                </div>
                                <div class="field">
                                    <label>Visibilité Employé</label>
                                    <select class="ui dropdown" name="employe">
                                        <option value="0" ${utilisateurProfil.voirEmploye=="0"?"selected":""}>
                                                NON
                                        </option>
                                        <option value="1" ${utilisateurProfil.voirEmploye=="1"?"selected":""}>
                                                OUI
                                        </option>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Visibilité Enseignant</label>
                                    <select class="ui dropdown" name="enseignant">
                                        <option value="0" ${utilisateurProfil.voirEnseignant=="0"?"selected":""}>
                                                NON
                                        </option>
                                        <option value="1" ${utilisateurProfil.voirEnseignant=="1"?"selected":""}>
                                                OUI
                                        </option>
                                    </select>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
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
