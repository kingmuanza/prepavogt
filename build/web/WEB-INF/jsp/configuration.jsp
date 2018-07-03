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
            Configuration            
        </h1>
        <div style="padding-top: 10px;">

            <div class="ui container">
                <div class="ui grid">
                    <div class="ten wide column">
                        <div>
                            <form class="ui form" action="AnneeScolaireServlet" method="post">
                                <div class="ui message">
                                    <div class="header">Dossier d'export de la pointeuse</div>
                                    <ul class="list">
                                        <li>Veuillez renseigner le dossier d'export de la pointeuse</li>
                                        <li>Si vous ne savez pas de quoi il est question, veuillez contacter l'administrateur</li>
                                    </ul>
                                </div>
                                <div class="required field">
                                    <label>Dossier d'export</label>
                                    <input type="text" name="code" value="C:/" required>
                                </div>
                                
                                <div>
                                    <button class="ui submit gris button" name="action" value="enregistrer" type="submit">
                                        Enregistrer
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
                ouvrirMenuCorrespondant("#section_params", "bouton_params", "anneeacademiques");

            })
        </script>
    </body>
</html>

