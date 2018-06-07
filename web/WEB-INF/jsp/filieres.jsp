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
        <h1 class="titre">Liste des filieres</h1>

        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Filieres</th>
                    <th>Code</th>
                    <th>Libelle</th>
                    <th>Etudiant</th>
                    <th>Cours</th>
                    <th>Cours Filieres</th>                 
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${filieres}" var="filiere">

                    <tr>
                        <td>
                            <h4 class="ui image header">
                                <img src="images/user.JPG" alt="Photo" class="ui mini rounded image">
                                <div class="content">
                                    ${filiere.code}
                                    <div class="sub header">
                                        ${filiere.libelle}
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>${filiere.code}</td>
                        <td>${filiere.libelle}</td>
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    <c:forEach items="${filiere.etudiants}" var="etudiant">
                                        ${etudiant.individu.noms}
                                    </c:forEach>
                                    <div class="sub header">
                                        <c:forEach items="${filiere.etudiants}" var="etudiant">
                                            ${etudiant.individu.prenoms}
                                        </c:forEach>
                                    </div>
                                </div>
                            </h4>
                        </td>
                        <td>
                            <c:forEach items="${filiere.courses}" var="cour">
                                ${cour.matiere.libelle}
                            </c:forEach>
                        </td>
                        <td>
                            <h4 class="ui image header">
                                <div class="content">
                                    <c:forEach items="${filiere.utilisateurProfilFilieres}" var="utilProfile">
                                        ${utilProfile.utilisateurProfil.code}
                                    </c:forEach>
                                    <div class="sub header">
                                        <c:forEach items="${filiere.utilisateurProfilFilieres}" var="utilProfile">
                                            ${utilProfile.utilisateurProfil.libelle}
                                        </c:forEach>
                                    </div>
                                </div>
                            </h4>
                        </td>
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
                $('#dataTableUtilisateur').DataTable({
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
