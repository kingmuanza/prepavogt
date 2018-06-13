<%-- 
    Document   : etudiant
    Created on : 11 juin 2018, 12:48:04
    Author     : zos hall
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <c:when test="${empty etudiant}">Nouvel Etudiant</c:when>
                <c:otherwise>Modifier / Supprimer un Etudiant</c:otherwise>
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
                                <a class="header">${etudiant.individu.noms} ${etudiant.individu.prenoms}</a>
                                <div class="meta">
                                    <span class="date">${etudiant.filiere.idfiliere}</span>
                                </div>
                                <div class="description">
                                    ${etudiant.niveauEtude.code} / ${etudiant.niveauEtude.code}
                                </div>
                            </div>
                            <div class="extra content">
                                <a>
                                    <i class="user icon"></i>
                                    ${etudiant.anneeScolaire.dateDebut} / ${etudiant.anneeScolaire.dateFin}
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="EtudiantServlet" method="post">
                                <c:if test="${!empty erreurs}">
                                    <div class="ui message">
                                        <div class="header">Messages à afficher en cas d'erreur</div>
                                        <ul class="list">
                                            <li>Entrez votre login</li>
                                            <li>Les mots de passe ne sont pas identiques</li>
                                        </ul>
                                    </div>
                                </c:if>
                                <input type="hidden" name="id" value="${etudiant.idetudiant}"/>
                                <div class="field">
                                    <label>Année Academique</label>
                                    <select class="ui dropdown" name="anneeSco">
                                        <option>Aucune annee</option>
                                        <c:forEach items="${anneeScolaires}" var="annee">
                                            <option value="${annee.idanneeScolaire}" ${etudiant.anneeScolaire.idanneeScolaire==annee.idanneeScolaire?"selected":""}>
                                                ${annee.dateDebut}/${annee.dateFin}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="field">
                                    <label>Niveau d'Etude</label>
                                    <select class="ui dropdown" name="niveau">
                                        <option>Aucun Niveau d'Etude</option>
                                        <c:forEach items="${niveauEtudes}" var="niv">
                                            <option value="${niv.idniveauEtude}" ${etudiant.niveauEtude.idniveauEtude==niv.idniveauEtude?"selected":""}>
                                                ${niv.code} ${niv.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="field">
                                    <label>Filiere</label>
                                    <select class="ui dropdown" name="filiere">
                                        <option>Aucune Filiere</option>
                                        <c:forEach items="${filieres}" var="f">
                                            <option value="${f.idfiliere}" ${etudiant.filiere.idfiliere==f.idfiliere?"selected":""}>
                                                ${f.code} ${f.libelle}
                                            </option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <label>Individu</label>
                                <div class="ui styled accordion">
                                    <div class="active title">

                                        <span style="color: black"><i class="dropdown icon"></i>Choisir l'individu</span>
                                    </div>
                                    <div class="active content">
                                        <p>
                                        <div class="field">
                                            <label>Individu</label>
                                            <select class="ui dropdown" name="individu">
                                                <option>Aucun individu</option>
                                                <c:forEach items="${individus}" var="i">
                                                    <option value="${i.idindividu}" ${etudiant.individu.idindividu==i.idindividu?"selected":""}>
                                                        ${i.noms} ${i.prenoms}
                                                    </option>
                                                </c:forEach>

                                            </select>
                                        </div>
                                        </p>
                                    </div>
                                    <c:if test="${empty etudiant}">
                                        <div class="title">
                                            <span style="color: black">
                                                <i class="dropdown icon"></i>
                                                Enregistrer l'individu
                                            </span>
                                        </div>
                                        <div class="content">
                                            <p>
                                            <div class="required field">
                                                <label>Matricule</label>
                                                <input type="text" name="matricule" value="" required>
                                            </div>
                                            <div class="two fields">
                                                <div class="required field">
                                                    <label>Civilite</label>
                                                    <input type="text" name="civilite" value="" required>
                                                </div>
                                                <div class="required field">
                                                    <label>Genre</label>
                                                    <select class="ui dropdown" name="matiere">
                                                        <option value="true" >Femme</option>
                                                        <option value="false">Homme</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="two fields">
                                                <div class="required field">
                                                    <label>Noms</label>
                                                    <input type="text" name="noms" value="" required>
                                                </div>
                                                <div class="required field">
                                                    <label>Prenoms</label>
                                                    <input type="text" name="prenoms" value="" required>
                                                </div>
                                            </div>
                                            <div class="two fields">
                                                <div class="required field">
                                                    <label>Date de naissance</label>
                                                    <input type="date" name="dateNaissance" value="" >
                                                </div>
                                                <div class="required field">
                                                    <label>Lieu de naissance</label>
                                                    <input type="text" name="lieuNaissance" value="" >
                                                </div>
                                            </div>
                                            <div class="two fields">
                                                <div class="required field">
                                                    <label>Residence</label>
                                                    <input type="text" name="residence" value="" >
                                                </div>
                                                <div class="required field">
                                                    <label>Adresse mail</label>
                                                    <input type="text" name="email" value="" >
                                                </div>
                                            </div>
                                            <div class="two fields">
                                                <div class="required field">
                                                    <label>Telephone 1</label>
                                                    <input type="text" name="telephone1" value="" >
                                                </div>
                                                <div class="field">
                                                    <label>Telephone 2</label>
                                                    <input type="text" name="telephone2" value="">
                                                </div>
                                            </div>
                                            </p>
                                        </div>
                                    </c:if>
                                </div>

                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
                                    </button>
                                    <c:if test="${!empty etudiant}">
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
