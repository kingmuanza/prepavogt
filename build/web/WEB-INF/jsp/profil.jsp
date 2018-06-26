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
            ${empty utilisateurProfil ? "Nouveau profil utilisateur":utilisateurProfil.libelle}
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
                            <form class="ui form" action="UtilisateurProfilServlet" method="post">
                                <div class="ui error message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez vos nouvelles paramettres</li>
                                        <li>Les paramettres renseignées ne sont pas conformes</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Code</label>
                                    <input type="hidden" name="id" value="${utilisateurProfil.idutilisateurProfil}">
                                    <input type="text" name="code" value="${utilisateurProfil.code}" required>
                                </div>
                                <div class="required field">
                                    <label>Libelle</label>
                                    <input type="text" name="libelle" value="${utilisateurProfil.libelle}">
                                </div>
                                <div class="field">
                                    <label>Visibilité Employé</label>
                                    <select class="ui dropdown" name="employe">
                                        <option value="0" ${!utilisateurProfil.voirEmploye?"selected":""}>
                                            Non
                                        </option>
                                        <option value="1" ${utilisateurProfil.voirEmploye?"selected":""}>
                                            Oui
                                        </option>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Visibilité Enseignant</label>
                                    <select class="ui dropdown" name="enseignant">
                                        <option value="0" ${!utilisateurProfil.voirEnseignant?"selected":""}>
                                            Non
                                        </option>
                                        <option value="1" ${utilisateurProfil.voirEnseignant?"selected":""}>
                                            Oui
                                        </option>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Filières</label>
                                    <div class="ui dropdown selection multiple">
                                        <i class="dropdown icon"></i>
                                        <div class="default text">Sélectionnez vos filières</div>
                                        <select id="multi-select" name="filieres" multiple>
                                            <option value="">Sélectionnez vos filières</option>
                                            <c:forEach items="${filieres}" var="filiere">
                                                <option value="${filiere.idfiliere}" ${filiereUtil.isIn(filiere, utilisateurProfil)?"selected":""}>
                                                    ${filiere.libelle}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div style="padding-top: 20px;">
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
                $("#multi-select").dropdown("get value") ;
            })
        </script>
    </body>
</html>
