<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
        <h1 class="titre" style="line-height: 1">
            Pointages en temps réel 
            <i style="float:right" class="spinner loading icon"></i>
        </h1>
        <div style="padding-bottom: 20px; font-size: 1.2em; opacity: 0.8">
            Dernière actualisation à 
            <span id="derniereHeure">
                <i class="spinner loading icon"></i>
            </span> 
        </div>
        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Heure</th>
                    <th>Matricule</th>
                    <th>Personne</th>                    
                    <th>Entrée/Sortie</th>                    
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${pointages}" var="p">

                    <tr>
                        <td>
                            <fmt:formatDate value="${p.heure}" pattern="HH:mm:ss"/>

                        </td>
                        <td>${p.matricule}</td>
                        <td>Tagne Edgar</td>
                        <td>Entrée</td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>




        <!-- Fichiers js pour le dataTable-->
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/dataTables.semanticui.min.js" type="text/javascript"></script>
        <script src="js/dataTables.responsive.min.js" type="text/javascript"></script>
        <script src="js/responsive.semanticui.min.js" type="text/javascript"></script>

        <!-- Datatable utilisateur -->
        <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="js/buttons.print.min.js" type="text/javascript"></script>
        <script src="js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="js/pdf.js.js" type="text/javascript"></script>
        <script src="js/pdfmake.min.js" type="text/javascript"></script>
        <script>
            var titre = 'Bonjour';
            var audio = new Audio('audio/sms-alert-3-daniel_simon.wav');

            var dernierIndex = 5;
            $(document).ready(function () {
                var table = $('#dataTableUtilisateur').DataTable({
                    ajax: {
                        url: "ActualiserPointageServlet?id=${temps}",
                        dataSrc: function (json) {
                            console.log(json);
                            return json;
                        }
                    },
                    "columns": [
                        {"data": "heure"},
                        {"data": "matricule"},
                        {"data": "noms"},
                        {"data": "entree"}
                    ],
                    "createdRow": function (row, data, dataIndex) {
                        if (data.heure > "08:00:00") {
                            $(row).addClass('negative ');
                        } else {
                            $(row).addClass('positive ');
                        }
                        console.log(data)
                        //$(row).addClass('active ');
                        dernierIndex = dataIndex;
                        //audio.play();

                        //console.log("dernierIndex : " + dernierIndex);
                        //console.log("dataIndex : " + dataIndex);
                    },
                    "order": [[0, "desc"]],
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    buttons: [
                        {
                            extend: 'excel',
                            text: "Exporter vers Excel",
                            title: titre,
                            message: '',
                            className: 'impressionExcel'
                        },
                        {
                            text: "Actualiser",
                            title: titre,
                            message: '',
                            className: 'actualiser ui gris mini button'
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

                table.on('xhr', function () {
                    var json = table.ajax;
                    //console.log(json);
                });

                setInterval(function () {
                    table.ajax.reload();
                    var d = new Date();
                    $("#derniereHeure").html(d.toISOString().substr(11, 8));
                }, 30000);

                $(".actualiser").on('click', function () {
                    table.ajax.reload();
                    var d = new Date();
                    $("#derniereHeure").html(d.toISOString().substr(11, 8));
                });

            });
        </script>

    </body>
</html>
