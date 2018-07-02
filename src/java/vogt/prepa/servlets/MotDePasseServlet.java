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
import vogt.prepa.dao.UtilisateurDAO;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "MotDePasseServlet", urlPatterns = {"/MotDePasseServlet"})
public class MotDePasseServlet extends HttpServlet {

    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Utilisateur u = utilisateurDAO.get(id);
                request.setAttribute("u", u);
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/motPasse.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect("start#!/");
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
        if (action != null && !action.isEmpty() && "modifier".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                String mot0 = request.getParameter("ancien");
                Utilisateur u = utilisateurDAO.get(id);
                if (verifierAvantSuppression(u)) {
                    if (mot0.equals(u.getPasse())){
                        String mot1 = request.getParameter("nouveau1");
                        String mot2 = request.getParameter("nouveau2");
                        if ((mot1.equals(mot2))&&(!mot1.equals(mot0))){
                            u.setPasse(mot2);
                            utilisateurDAO.enregistrer(u);
                            Notification notif = new Notification();
                            notif.setTitre("Suppression");
                            notif.setMessage("L'élement a bien été supprimé");
                            notif.setSuccess(true);
                            notifications.add(notif);
                            httpSession.setAttribute("notifications", notifications);
                            response.sendRedirect("start#!/utilisateur/"+u.getIdutilisateur());
                        }else{
                            Notification notif = new Notification();
                            notif.setTitre("Suppression");
                            notif.setMessage("Echec de suppression !");
                            notif.setSuccess(false);
                            notifications.add(notif);
                            httpSession.setAttribute("notifications", notifications);
                        }
                    }else{
                        response.sendRedirect("start#!/motDePasse");
                    }
                }
            }
        }
        else {
            response.sendRedirect("start#!/motDePasse");
        }
    }
    
    public boolean verifierAvantSuppression(Utilisateur u) {
        return true;
    }

}
