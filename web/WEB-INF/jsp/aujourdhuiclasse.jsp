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
        <style>
            .cacher{
                display: none;
            }
        </style>

    </head>
    <body>
        <h1 class="titre">
            Aujourd'hui
            <b>${classe.niveauEtude.code} ${classe.filiere.libelle}</b>
        </h1>

        <div  style="background-color: #f8f8f8; border-radius: 5px; border: 1px solid #004d6f">
            <div style="padding-top: 25px; padding-bottom : 25px">


                <div class="ui four statistics">
                    <div class="statistic">
                        <div class="value titre" id="moyenne">
                            ${stat.getMoyenneHeure()}
                        </div>
                        <div class="label">
                            Moyenne d'arrivée
                        </div>
                    </div>

                    <div class="statistic">
                        <div class="value  titre">
                            <img src="img/joe.jpg" class="ui circular inline image">
                            <span id="initial">

                            </span>
                        </div>
                        <div class="label" id="minimum">

                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre" id="abs">
                            ${stat.getAbsents().size()}
                        </div>
                        <div class="label">
                            Absents
                        </div>
                    </div>

                    <div class="statistic">
                        <div class="value  titre" id="ret">
                            ${stat.getAbsents().size()}
                        </div>
                        <div class="label">
                            Retards
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <div>
            <div style="padding-top: 25px; padding-bottom : 25px">
                <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
                    <thead>
                        <tr>
                            <th>Individu</th>
                            <th>Matricule</th>
                            <th>Date</th>     
                            <th>Heure</th>     
                        </tr>
                    </thead>

                    <tbody>
                        <c:set var = "i" value = "${0}"/>
                        <c:set var = "r" value = "${0}"/>
                        <c:set var = "total" value = "${0}"/>
                        <c:set var = "moyenne" value = "${0}"/>
                        <c:set var = "p" value = ""/>
                        <c:set var = "n" value = ""/>
                        <jsp:useBean id="min" class="java.util.Date"/>

                        <c:forEach items="${classe.etudiants}" var="etudiant">
                            <c:set var = "pointage" value = "${edgarServiceImpl.premierPointage(etudiant.individu, jourLa)}"/>
                            <c:if test="${!empty pointage}">
                                <c:set var = "i" value = "${i+1}"/>
                            </c:if>
                            <c:if test="${!empty pointage && pointage.heure>huit}">
                                <c:set var = "r" value = "${r+1}"/>
                            </c:if>
                            
                            <tr class = "pointeur ${empty pointage ?'negative':''}">
                                <td>
                                    ${etudiant.individu.noms}
                                    ${etudiant.individu.prenoms}
                                </td>
                                <td>${etudiant.individu.matricule}</td>

                                <td>
                                    <fmt:formatDate value="${pointage.heure}" pattern="yyyy-MM-dd"/>

                                    <c:set var = "total" value = "${total + pointage.heure.getTime()}"/>
                                    <c:if test="${pointage.heure < min}">
                                        <c:set var = "min" value = "${pointage.heure}"/>
                                        <c:set var = "p" value = "${etudiant.individu.noms.substring(0,1)}"/>
                                        <c:set var = "n" value = "${etudiant.individu.prenoms.substring(0,1)}"/>
                                    </c:if>
                                </td>
                                <td class="${!empty pointage && pointage.heure>huit? 'negative':'positive'}">
                                <fmt:formatDate value="${pointage.heure}" pattern="HH:mm:ss"/>
                                </td>

                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
                <p id="total" class="cacher">
                    <jsp:useBean id="ourDate" class="java.util.Date"/>
                    <jsp:setProperty name="ourDate" property="time" value="${total/i}"/>
                    <fmt:formatDate value="${ourDate}" pattern="HH:mm"/>

                </p>
                <p id="min" class="cacher">
                    <fmt:formatDate value="${min}" pattern="HH:mm:ss"/>
                </p>
                <p id="pn" class="cacher">
                    ${n}${p}
                </p>
                <p id="absents" class="cacher">
                    ${classe.etudiants.size()-i}
                </p>
                <p id="retards" class="cacher">
                    ${r}
                </p>

            </div>
        </div>



        <!-- Datatable -->
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/jszip.js" type="text/javascript"></script>
        <script src="js/pdf.js.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script>
            var titre = 'Bonjour';
            $(document).ready(function () {
                var moyenne = $('#total').html();
                $('#moyenne').html(moyenne);
                var min = $('#min').html();
                $('#minimum').html(min);
                var pn = $('#pn').html();
                $('#initial').html(pn);
                var absents = $('#absents').html();
                $('#abs').html(absents);
                var retards = $('#retards').html();
                $('#ret').html(retards);
                
                ouvrirMenuCorrespondant("#section_pointeuse", "bouton_pointeuse", "pointages");

                $('#dataTableUtilisateur').DataTable({
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    "order": [[0, "desc"]],
                    buttons: [
                        
                        {
                            extend: 'excelHtml5',
                            text: "Exporter vers Excel",
                            title: titre,
                            message: '',
                            className: 'ui gris mini basic button'
                        },
                        {
                            extend: 'print',
                            text: "Imprimer",
                            title: titre,
                            message: '',
                            className: 'impression ui gris basic mini button'
                        }
                    ],
                    "language": {
                        "sEmptyTable": "Aucune donnée disponible",
                        "sInfo": "Affiche _START_ à _END_ sur _TOTAL_ entrées",
                        "sLengthMenu": "Afficher _MENU_ lignes par page",
                        "sSearch": "Rechercher : ",
                        "zeroRecords": "Aucun résultat",
                        "info": "Page _PAGE_ sur _PAGES_",
                        "infoEmpty": "Aucun résultat disponible",
                        "sProcessing": "Veuillez patienter...",
                        "infoFiltered": "(sur les _MAX_ disponibles)",
                        "paginate": {
                            "previous": "Précédent",
                            "next": "Suivant"
                        }
                    }
                });
            });
        </script>

    </body>
</html>
