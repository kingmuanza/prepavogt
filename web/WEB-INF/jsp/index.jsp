<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PREPA VOGT</title>

        <!-- Fichiers CSS-->
        <link href="css/semantic.css" rel="stylesheet" type="text/css"/>
        <link href="css/myapp.css" rel="stylesheet" type="text/css"/> 
    </head>


    <body style="height: 100vh; overflow-y: hidden; color: white">

        <div class="ui fluid four column doubling stackable grid">
            <div class="two wide column gris">

            </div>
            <div class="four wide column gris">
                <div class="container" style="padding-top: 40px;">
                    <div>
                        <img class="image moyen rond" src="img/logo.png" alt="" onmouseover="bouger()"/>
                    </div>
                    <h1 class="espace_haut" style="line-height: 1; white-space: nowrap; font-size: 3.0em" >
                        PREPA VOGT
                    </h1>
                    <div style="font-size: 2.0em; font-weight: 100; color: #00B5AD">
                        Ora et Labora
                    </div>
                    <div style="padding-top: 40px;">
                        <form class="ui form" method="post" action="ConnexionServlet">
                            <div class="field">
                                <label class="fg-white">Login</label>
                                <input type="text" name="login" placeholder="Login">
                            </div>
                            <div class="field" style="padding-bottom: 20px;">
                                <label class="fg-white">Mot de passe</label>
                                <input type="password" name="passe" >
                            </div>
                            <button class="ui teal button" type="submit">Connexion</button>
                        </form>
                    </div>


                </div>
            </div>
            <div class="two wide column gris" style="">

            </div>
            
            
            <!--Animation particles-->
            <div class="eight wide column" style="background-color: #fdfdff">
                <div id="hero-wrapper" style="border : 0px solid #000; width: 100%; height: 100vh">
                    <div style="position: absolute; bottom: 0; right: 0; padding: 40px; color : #00B5AD!important; z-index: 159874">
                        &copy;  SIA TECHNOLOGY GROUP
                    </div>
                </div>
            </div>
        </div>


        <!-- Fichiers JS-->
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="particles/particles.min.js" type="text/javascript"></script>
        <script src="js/semantic.js" type="text/javascript"></script>
        <script src="js/myapp.js" type="text/javascript"></script>
        
        <script>
            particlesJS.load('hero-wrapper', 'particles/particles.json', function () {});
        </script>
        
    </body>
</html>
