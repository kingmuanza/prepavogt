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
            Historiques de pointage ${plus}
        </h1>

        <div  style="background-color: #f8f8f8; border-radius: 5px; border: 1px solid #004d6f; display: none;">
            <div style="padding-top: 25px; padding-bottom : 25px">


                <div class="ui three statistics">
                    <div class="statistic">
                        <div class="value titre">
                            07:49
                        </div>
                        <div class="label">
                            Moyenne d'arrivée
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="text value  titre">
                            2A<br>
                            Ingénieur
                        </div>
                        <div class="label">
                            Ponctualité
                        </div>
                    </div>
                    <div class="statistic">
                        <div class="value  titre">
                            <i class="clock outline icon"></i>
                        </div>
                        <div class="label">
                            06:35
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div>
            <div style="padding-top: 5px; padding-bottom : 25px">
                <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
                    <thead>
                        <tr>
                            <th>Date</th>                    
                            <th>Heure</th>                    
                            <th>Numéro</th>
                            <th>Matricule</th>
                            <th>Noms</th>
                            <th>Prénoms</th>
                            <th>Mode</th>
                            <th>I/O MD</th>                    
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${pointages}" var="pointage">
                            <tr class = "pointeur" onclick="window.location.href = 'start#!/pointage/${pointage.machine}'">
                                <td>
                                    <fmt:formatDate pattern="yyyy-MM-dd" value="${pointage.heure}"/> 
                                </td>
                                <td>
                                    <fmt:formatDate pattern="HH:mm:ss" value="${pointage.heure}"/> 
                                </td>
                                <td>${pointage.numero}</td>
                                <td>${pointage.matricule}</td>
                                <td>
                                    ${individuDAO.selectionnerIndividu(pointage.matricule, individus).getNoms()}
                                </td>
                                <td>
                                    ${individuDAO.selectionnerIndividu(pointage.matricule, individus).getPrenoms()}
                                </td>
                                <td>${pointage.mode}</td>
                                <td>${pointage.iomd}</td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
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
                            
                            ouvrirMenuCorrespondant("#section_pointeuse", "bouton_pointeuse", "pointages");
                            
                            $('#dataTableUtilisateur').DataTable({
                                dom: '<"top"fB>rt<"bottom"lp><"clear">',
                                "order": [[ 0, "desc" ]],
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
