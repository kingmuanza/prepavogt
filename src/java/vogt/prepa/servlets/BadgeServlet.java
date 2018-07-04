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
import vogt.prepa.dao.BadgeDAO;
import vogt.prepa.entities.Badge;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "BadgeServlet", urlPatterns = {"/BadgeServlet"})
public class BadgeServlet extends HttpServlet {

    BadgeDAO badgeDAO = new BadgeDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Badge badge = badgeDAO.get(i);
                request.setAttribute("badge", badge);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/badge.jsp").forward(request, response);
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
        //System.out.print("Action : " + action);
        //Pour supprimer l'entité
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            String id = request.getParameter("id");
            //System.out.print("ID : " + id);
            if (id != null && !id.isEmpty()) {
                Badge b = badgeDAO.get(id);
                if (verifierAvantSuppression(b)) {
                    badgeDAO.supprimer(b);
                    //System.out.println("Suppression reussie!");
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/badges");
                } else {
                    //System.out.println("Suppression echoue!");
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
            Badge b = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                b = badgeDAO.get(i);
            } else {
                b = new Badge();
            }
            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            
            b.setCode(code);
            b.setLibelle(libelle);
            
            if (badgeDAO.enregistrer(b)) {
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
            response.sendRedirect("start#!/badge/"+b.getIdbadge());
        }
    }

    public boolean verifierAvantSuppression(Badge b) {
        return true;
    }

}
