<%-- 
    Document   : pointages
    Created on : 11 juin 2018, 12:51:05
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
        <h1 class="titre">Historique des pointages</h1>
        <h4 style="margin-top: -10px; opacity: 0.5">${individu.noms} ${individu.prenoms}</h4>
        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Heure</th>                    
                    <th>Numero</th>
                    <th>Matricule</th>
                    <th>Individu</th>
                    <th>Mode</th>
                    <th>I/O MD</th>                    
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${pointages}" var="pointage">
                    <tr class = "pointeur" onclick="window.location.href = 'start#!/pointage/${pointage.machine}'">
                        <td>${pointage.heure}</td>
                        <td>${pointage.numero}</td>
                        <td>${pointage.machine}</td>
                        <td>
                            individuDAO.getByMatricule(pointage.machine).getNoms()
                            individuDAO.getByMatricule(pointage.machine).getPrenoms()
                        </td>
                        <td>${pointage.mode}</td>
                        <td>${pointage.iomd}</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>




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
                            $('#dataTableUtilisateur').DataTable({
                                dom: '<"top"fB>rt<"bottom"lp><"clear">',
                                "order": [[ 0, "desc" ]],
                                buttons: [
                                    {
                                        text: "Nouveau",
                                        title: titre,
                                        message: '',
                                        className: 'ui gris mini button',
                                        action: function (e, dt, node, config) {
                                            window.location.href = 'start#!/pointage'
                                        }
                                    },
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
