/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import vogt.prepa.dao.IndividuDAO;
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
    IndividuDAO individuDAO = new IndividuDAO();
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
            request.setAttribute("individus", individuDAO.getall());
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
        } else {
            String id = request.getParameter("id");
            Entree e = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                e = entreeDAO.get(i);
            } else {
                e = new Entree();

            }
            String individu = request.getParameter("indiv");
            e.setIndividu(individuDAO.get(individu));
            String visite = request.getParameter("visite");
            e.setVisite(visiteDAO.get(visite));
            String badge = request.getParameter("badg");
            e.setBadge(badgeDAO.get(badge));
            
            String nomComplet = request.getParameter("nomComplet");
            e.setNomComplet(nomComplet);
            String motif = request.getParameter("motif");
            e.setMotif(motif);
            String commentaire = request.getParameter("commentaire");
            e.setCommentaire(commentaire);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String debut = request.getParameter("dateEntree");
            String fin = request.getParameter("dateSortie");
            Date dateEntree = null;
            Date dateSortie = null;
            try {
                
                
                dateEntree = sdf.parse(debut);
                
            } catch (ParseException el) {
                System.out.println("la date entrée est fausse, retapez la date");
            }
            e.setDateEntree(dateEntree);
            try {
                 dateSortie = sdf.parse(fin);
                
            } catch (ParseException el) {
                System.out.println("la date final entrée est fausse, retapez la date");
            }
            e.setDateSortie(dateSortie);

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
