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
        <h1 class="titre">Liste des collaborateurs</h1>

        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Individu</th>
                    <th>Poste</th>
                    <th>Date et lieu naiss.</th>
                    <th>Contacts</th>                    
                    <th>Résidence</th>                    
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${employes}" var="employe">

                    <tr>

                        <td>
                            <h4 class="ui image header">
                                <img src="images/user.JPG" alt="Photo" class="ui mini rounded image">
                                <div class="content">
                                    ${employe.individu.noms} ${employe.individu.prenoms}
                                    <div class="sub header">
                                        ${employe.individu.genre ? "Femme":"Homme"}
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    ${employe.poste.libelle}
                                    <div class="sub header">
                                        ${employe.poste.code}
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    ${employe.individu.datenaiss}
                                    <div class="sub header">
                                        ${employe.individu.lieunaiss}
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    ${employe.individu.email}
                                    <div class="sub header">
                                        ${employe.individu.tel1}
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>${employe.individu.residence}</td>
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
            $(document).ready(function () {

                ouvrirMenuCorrespondant("#section_params", "bouton_params", "collaborateurs");

                $('#dataTableUtilisateur').DataTable({
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    "order": [[1, "asc"]],
                    buttons: [
                        {
                            extend: 'excel',
                            text: "Exporter vers Excel",
                            title: titre,
                            message: '',
                            className: 'impressionExcel'
                        },
                        {
                            text: "Nouveau",
                            title: titre,
                            message: '',
                            className: ' ui gris mini button'
                        },
                        {
                            extend: 'pdfHtml5',
                            text: "Exporter en PDF",
                            title: titre,
                            message: '',
                            className: 'impressionPDF ui gris basic mini button'
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
