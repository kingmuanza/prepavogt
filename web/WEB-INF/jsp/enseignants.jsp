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
            #muanza span {
                display: inline-block;
                width:150px;
                overflow: hidden;
                text-overflow: ellipsis; 
                padding-right: 10px;
            }
        </style>

    </head>
    <body>
        <h1 class="titre">Liste des enseignants</h1>

        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Enseignants</th>
                    <th>Cours dispensés</th>                   
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${enseignants}" var="enseignant">

                    <tr class="pointeur" onclick="window.location.href='start#!/enseignant/${enseignant.idenseignant}'">
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    ${enseignant.individu.noms} ${enseignant.individu.prenoms}
                                    <div class="sub header">
                                        ${enseignant.individu.genre ? "Femme":"Homme"}
                                    </div>
                                </div>
                            </h4>
                        </td>

                        <td>
                            <h4 class="ui image header">
                                <div class="content" id="muanza">
                                    <c:forEach items="${enseignant.coursEnseignants}" var="courEn">
                                        <span>
                                            ${courEn.cours.matiere.libelle}&nbsp;
                                        </span>
                                    </c:forEach>
                                    <div class="sub header">
                                        <c:forEach items="${enseignant.coursEnseignants}" var="courEn">
                                            <span>
                                                ${courEn.cours.classe.niveauEtude.code} 
                                                ${courEn.cours.classe.filiere.libelle}&nbsp;
                                            </span>
                                        </c:forEach>
                                    </div>


                                </div>
                            </h4>
                        </td>
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

                ouvrirMenuCorrespondant("#section_params", "bouton_params", "enseignants");

                $('#dataTableUtilisateur').DataTable({
                    dom: '<"top"fB>rt<"bottom"lp><"clear">',
                    buttons: [
                        {
                            text: "Nouveau",
                            title: titre,
                            message: '',
                            className: 'ui gris mini button',
                            action: function (e, dt, node, config) {
                                window.location.href='start#!/enseignant'
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
