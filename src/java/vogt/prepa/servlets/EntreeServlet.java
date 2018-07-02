/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.BadgeDAO;
import vogt.prepa.dao.EntreeDAO;
import vogt.prepa.dao.VisiteDAO;
import vogt.prepa.entities.Entree;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.entities.Visite;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "EntreeServlet", urlPatterns = {"/EntreeServlet"})
public class EntreeServlet extends HttpServlet {

    EntreeDAO entreeDAO = new EntreeDAO();
    BadgeDAO badgeDAO = new BadgeDAO();
    VisiteDAO visiteDAO = new VisiteDAO();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Entree entree = entreeDAO.get(i);
                request.setAttribute("entree", entree);

            }
            request.setAttribute("visites", visiteDAO.getall());
            request.setAttribute("badges", badgeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/entree.jsp").forward(request, response);
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
                Entree e = entreeDAO.get(id);
                if (verifierAvantSuppression(e)) {
                    entreeDAO.supprimer(e);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/entrees");
                } else {
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("Echec de suppression !");
                    notif.setSuccess(false);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                }
            }//Pour ajouter ou modifier l'entité
        } else if (action != null && !action.isEmpty() && "sortir".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Entree e = entreeDAO.get(id);
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar cal = Calendar.getInstance();
                String dateee = sdf.format(cal.getTime());
                System.out.println(sdf.format(cal.getTime()));

                Date dateSortie = new Date();

                try {

                    dateSortie = sdf.parse(dateee);

                } catch (ParseException el) {
                    System.out.println("la date final entrée est fausse, retapez la date");
                }

                e.setDateSortie(dateSortie);

                if (verifierAvantSuppression(e)) {
                    entreeDAO.enregistrer(e);
                    Notification notif = new Notification();
                    notif.setTitre("Sortie");
                    notif.setMessage("Date de sorti mise à jour avec succès");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/entrees");
                } else {
                    Notification notif = new Notification();
                    notif.setTitre("Sortie");
                    notif.setMessage("Echec de mise à jour de la date de sortie !");
                    notif.setSuccess(false);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                }
            }
        } else {
            String id = request.getParameter("id");
            Entree e = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                e = entreeDAO.get(i);
            } else {
                e = new Entree();

            }
            String badge = request.getParameter("badg");
            e.setBadge(badgeDAO.get(badge));

            String nomComplet = request.getParameter("nomComplet");
            e.setNomComplet(nomComplet);
            String motif = request.getParameter("motif");
            e.setMotif(motif);
            String commentaire = request.getParameter("commentaire");
            e.setCommentaire(commentaire);

            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String dateee = sdf.format(cal.getTime());
            System.out.println(sdf.format(cal.getTime()));

            Date dateEntree = new Date();

            try {

                dateEntree = sdf.parse(dateee);

            } catch (ParseException el) {
                System.out.println("la date final entrée est fausse, retapez la date");
            }

            e.setDateEntree(dateEntree);

            if (entreeDAO.enregistrer(e)) {
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
            response.sendRedirect("start#!/entree/" + e.getIdentree());
        }
    }

    public boolean verifierAvantSuppression(Entree e) {
        return true;
    }
}
