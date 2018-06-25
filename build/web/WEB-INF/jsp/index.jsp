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
        <link href="css/myapp.css?id=145" rel="stylesheet" type="text/css"/> 
        <link href="css/Semantic-UI-Alert.css" rel="stylesheet" type="text/css"/>
    </head>


    <body style="height: 100vh; overflow-y: hidden; color: white">

        <div class="ui fluid four column doubling stackable grid">
            <div class="two wide column ${empty sessionScope.connexionError?'gris':'bg-red' } ">

            </div>
            <div class="four wide column ${empty sessionScope.connexionError?'gris':'bg-red' } ">
                <div class="container" style="padding-top: 40px;">
                    <div>
                        <img class="image moyen rond ${empty sessionScope.connexionError?'':'error' }" src="img/logo.png" alt="" onmouseover="bouger()"/>
                    </div>
                    <h1 class="espace_haut" style="line-height: 1; white-space: nowrap; font-size: 3.0em" >
                        PREPA VOGT
                    </h1>
                    <div class="${empty sessionScope.connexionError?'fg-teal':'fg-grey' } " style="font-size: 2.0em; font-weight: 100;">
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
                            <button class="ui ${empty sessionScope.connexionError?'teal':'' } button" type="submit">Connexion</button>
                        </form>
                    </div>



                </div>
            </div>
            <div class="two wide column ${empty sessionScope.connexionError?'gris':'bg-red' } " style="">

            </div>


            <!--Animation particles-->
            <div class="eight wide column" style="background-color: #fdfdff">
                <div id="hero-wrapper" style="border : 0px solid #000; width: 100%; height: 100vh">
                    <div class="${empty sessionScope.connexionError?'fg-teal':'fg-grey' }" style="position: absolute; bottom: 0; right: 0; padding: 40px;z-index: 159874">
                        &copy;  SIA TECHNOLOGY GROUP
                    </div>
                </div>
            </div>
        </div>


        <!-- Fichiers JS-->
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/semantic.js" type="text/javascript"></script>
        <script src="particles/particles.min.js" type="text/javascript"></script>
        <script src="js/Semantic-UI-Alert.js" type="text/javascript"></script>
        <script src="js/myapp.js" type="text/javascript"></script>

        <script>
                            particlesJS.load('hero-wrapper', 'particles/particles.json', function () {});
                            $(document).ready(function () {
                                $('.ui.dropdown').dropdown();
                                console.log("Moi je comprend pas pas");
            <c:if test="${!empty sessionScope.connexionError}">

                                $.uiAlert({
                                    textHead: "Erreur",
                                    text: "${sessionScope.connexionError}",
                                    bgcolor: '#DB2828',
                                    textcolor: '#fff',
                                    position: 'top-right', // top And bottom ||  left / center / right
                                    icon: 'bell outline icon',
                                    permanent: true
                                });

            </c:if>


                            });
        </script>

    </body>
</html>
