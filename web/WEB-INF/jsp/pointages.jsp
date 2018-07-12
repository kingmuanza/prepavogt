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
        <c:if test="${!empty dates}">

            <div style="padding-bottom: 20px; line-height: normal">
                <div style="padding : 20px; background-color: #f8f8f8; border-radius: 5px; border: 1px solid #004d6f">
                    <div style="font-size: 1.2em">
                        Choisissez une date
                    </div>
                    <c:forEach items="${dates}" var="d">
                        <fmt:formatDate pattern="yyyyMMdd" value = "${d}" var="dateJour" />
                        <div onclick="window.location.href = 'start#!/pointages/${dateJour}'" class="ui ${dateJour==jour?"gris":""} label" style="margin-top: 10px; cursor:pointer" >
                            <i class="calendar alternate icon"></i> 
                            <fmt:formatDate type = "date" dateStyle = "medium" value = "${d}" />
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <h4 style="margin-top: -10px; opacity: 0.5">${individu.noms} ${individu.prenoms}</h4>
        <table id="dataTableUtilisateur" class="ui celled table responsive nowrap" style="width:100%">
            <thead>
                <tr>
                    <th>Jour</th>                    
                    <th>Heure</th>                    
                    
                   <th>Matricule</th>
                    <th>Individu</th>
                                        
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${pointages}" var="pointage">
                    <tr class = "pointeur" onclick="window.location.href = 'start#!/pointage/${pointage.matricule}'">
                        <td>
                            <fmt:formatDate pattern="yyyy-MM-dd" value = "${pointage.heure}" />
                            
                        </td>
                        <td>
                            <fmt:formatDate pattern="HH:mm:ss" value = "${pointage.heure}" />
                        </td>
                        <td>
                            ${pointage.matricule}
                            
                        </td>
                        <td>
                            ${individuDAO.selectionnerIndividu(pointage.matricule, individus).getNoms()}
                            ${individuDAO.selectionnerIndividu(pointage.matricule, individus).getPrenoms()}
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
