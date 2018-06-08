<%-- 
    Document   : enregistrementIndividu
    Created on : 7 juin 2018, 11:28:48
    Author     : 12Lions
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <div class="ui error form segment">
            <div class="ui error message">
                <div class="header">Division pour les alertes</div>
                <p>Message d'alertes (Succès, echec, validation, etc..)</p>
            </div>
            <div class="two fields">
                <div class="field">
                    <label>Nom</label>
                    <input placeholder="Votre Nom" type="text" name="noms" required>
                </div>
                <div class="field">
                    <label>Prénoms</label>
                    <input placeholder="Votre Prénom" type="text" name="prenoms" required>
                </div>
            </div>
            <div class="field">
                <label>Genre</label>
                <div class="ui fluid selection dropdown">
                    <div class="text">Select</div>
                    <i class="dropdown icon"></i>
                    <input type="hidden" name="genre" required>
                    <div class="menu">
                        <div class="item" data-value="0">Masculin</div>
                        <div class="item" data-value="1">Feminin</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>Civilité</label>
                <div class="ui fluid selection dropdown">
                    <div class="text">Select</div>
                    <i class="dropdown icon"></i>
                    <input type="hidden" name="gender">
                    <div class="menu">
                        <div class="item" data-value="Monsieur" name="genre">Monsieur</div>
                        <div class="item" data-value="Madame" name="genre">Madame</div>
                        <div class="item" data-value="Mademoiselle" name="genre">Mademoiselle</div>
                    </div>
                </div>
            </div>
            <div class="field">
                <label>Date de naissance</label>
                <input type="date" name="dateNaiss" required>
            </div>
            <div class="inline field">
                <div class="ui checkbox">
                    <input type="checkbox">
                    <label>I agree to the Terms and Conditions</label>
                </div>
            </div>
            <div class="ui blue submit button">Submit</div>
        </div>
    </body>
</html>
