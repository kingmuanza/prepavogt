<%-- 
    Document   : motPasse
    Created on : 2 juil. 2018, 13:32:44
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
            Voulez-vous changer votre mot de passe utilisateur ?
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
                                    ${u.individu.noms} ${u.individu.prenoms}
                                </a>
                                <div class="description">
                                    ${u.login}
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    ${empty u.utilisateurProfil?"":u.utilisateurProfil.libelle}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="MotDePasseServlet" method="post">
                                <div class="ui error message">
                                    <div class="header">Messages à afficher en cas d'erreur</div>
                                    <ul class="list">
                                        <li>Entrez votre login</li>
                                        <li>Les mots de passe ne sont pas identiques</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Login</label>
                                    <input type="hidden" name="id" value="${sessionScope.utilisateur.idutilisateur}" required>
                                    <input type="text" name="login" value="${sessionScope.utilisateur.login}" required>
                                </div>
                                <div class="field">
                                    <div class="required field">
                                        <label>Veuillez renseigner l'ancien Mot de passe</label>
                                        <input type="password" name="ancien" value="${sessionScope.utilisateur.passe}">
                                    </div>
                                </div>
                                <div class="two fields">
                                    <div class="required field">
                                        <label>Nouveau Mot de passe</label>
                                        <input type="password" name="nouveau1" >
                                    </div>
                                    <div class="required field">
                                        <label>Confirmer nouveau Mot de passe</label>
                                        <input type="password" name="nouveau2" >
                                    </div>
                                </div>
                                <div>
                                    <button class="ui submit gris button" name="action" value="modifier" type="submit">
                                        Modifier
                                    </button>
                                    <button class="ui submit red button" name="action" value="supprimer" type="submit">
                                        Supprimer
                                    </button>
                                </div>

                            </form>
                            <div class="ui basic modal">
                                <div class="ui icon header">
                                    <form class="ui form" action="MotDePasseServlet" method="post">
                                        <p>Veuillez confirmer le changement de mot de passe ?</p>
                                        <input type="hidden" name="id" value="${sessionScope.utilisateur.idutilisateur}">
                                        <div >
                                            <button class="ui cancel gris button" >
                                                Annuler
                                            </button>
                                            <button class="ui submit red button" name="action" value="modifier" type="submit">
                                                Confirmer
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "utilisateurs");
                
            })
        </script>
    </body>
</html>
