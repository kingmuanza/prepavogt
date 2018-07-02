/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.VisiteDAO;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.entities.Visite;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "VisiteServlet", urlPatterns = {"/VisiteServlet"})
public class VisiteServlet extends HttpServlet {

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
                Visite visite = visiteDAO.get(i);
                request.setAttribute("visite", visite);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/visite.jsp").forward(request, response);
        }else{
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
                Visite v = visiteDAO.get(id);
                if (verifierAvantSuppression(v)) {
                    visiteDAO.supprimer(v);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/visites");
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
            Visite v = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                v = visiteDAO.get(i);
            } else {
                v = new Visite();

            }
            String nomComplet = request.getParameter("nomComplet");
            v.setNomComplet(nomComplet);
            String motif = request.getParameter("motif");
            v.setMotif(motif);
            String commentaire = request.getParameter("commentaire");
            v.setCommentaire(commentaire);
    
            if (visiteDAO.enregistrer(v)) {
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
            response.sendRedirect("start#!/visite/" + v.getIdvisite());
        }
    }

    public boolean verifierAvantSuppression(Visite v) {
        return true;
    }
}
