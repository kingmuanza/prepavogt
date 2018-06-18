/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.AnneeScolaireDAO;
import vogt.prepa.dao.EtudiantDAO;
import vogt.prepa.entities.AnneeScolaire;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "AnneeScolaireServlet", urlPatterns = {"/AnneeScolaireServlet"})
public class AnneeScolaireServlet extends HttpServlet {

    AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO();
    EtudiantDAO etudiantDAO = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                AnneeScolaire anneeScolaire = anneeScolaireDAO.get(id);
                request.setAttribute("anneeScolaire", anneeScolaire);
                request.setAttribute("etudiants", etudiantDAO.getall());

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/anneeacademique.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        List<Notification> notifications = (List<Notification>) httpSession.getAttribute("notifications");
        String action = request.getParameter("action");
        System.out.print("Action : " + action);
        //Pour supprimer l'entité
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                AnneeScolaire annee = anneeScolaireDAO.get(id);
                if (verifierAvantSuppression(annee)) {
                    anneeScolaireDAO.supprimer(annee);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/anneeacademiques");
                } else {
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("Echec de suppression !");
                    notif.setSuccess(false);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                }
            }
        } //Pour ajouter ou modifier l'entité
        else {
            String id = request.getParameter("id");
            AnneeScolaire annee = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                annee = anneeScolaireDAO.get(i);
            } else {
                annee = new AnneeScolaire();
            }

            String code = request.getParameter("code");
            annee.setCode(code);
            String libelle = request.getParameter("libelle");
            annee.setLibelle(libelle);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String debut = request.getParameter("dateDebut");
                Date dateDebut = sdf.parse(debut);
                annee.setDateDebut(dateDebut);
            } catch (ParseException el) {
                System.out.println("la date entrée est fausse, retapez la date");
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fin = request.getParameter("dateFin");
                Date dateFin = sdf.parse(fin);
                annee.setDateFin(dateFin);
            } catch (ParseException el) {
                System.out.println("la date final entrée est fausse, retapez la date");
            }

            if (anneeScolaireDAO.enregistrer(annee)) {
                Notification notif = new Notification();
                notif.setTitre("Enregistrement");
                notif.setMessage("L'élement a bien été enregistré");
                notif.setSuccess(true);
                notifications.add(notif);
                httpSession.setAttribute("notifications", notifications);
            } else {
                Notification notif = new Notification();
                notif.setTitre("Enregistrement");
                notif.setMessage("Echec d'enregistrement");
                notif.setSuccess(true);
                notifications.add(notif);
                httpSession.setAttribute("notifications", notifications);
            }

            response.sendRedirect("start#!/anneeacademique/" + annee.getIdanneeScolaire());
        }
    }

    public boolean verifierAvantSuppression(AnneeScolaire annee) {
        return true;
    }
}
