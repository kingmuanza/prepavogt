/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
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
import vogt.prepa.dao.PointageDAO;
import vogt.prepa.entities.Pointage;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "PointageServlet", urlPatterns = {"/PointageServlet"})
public class PointageServlet extends HttpServlet {

    PointageDAO pointageDAO = new PointageDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Pointage pointage = pointageDAO.get(i);
                request.setAttribute("pointage", pointage);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pointage.jsp").forward(request, response);
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
                Pointage p = pointageDAO.get(id);
                if (verifierAvantSuppression(p)) {
                    pointageDAO.supprimer(p);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/pointages");
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
            Pointage p = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                p = pointageDAO.get(i);
            } else {
                p = new Pointage();
            }
            String numero = request.getParameter("numero");
            String matricule = request.getParameter("matricule");
            String machine = request.getParameter("machine");
            String mode = request.getParameter("mode");
            String iomd = request.getParameter("iomd");
            String heur = request.getParameter("heure");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date heure = null;
            
            try {
                heure = sdf.parse(heur);
            } catch (ParseException el) {
                System.out.println("la date final entrée est fausse, retapez la date");
            }
            
            p.setNumero(numero);
            p.setMatricule(matricule);
            p.setMachine(machine);
            p.setMode(mode);
            p.setIomd(iomd);
            p.setHeure(heure);
            if (pointageDAO.enregistrer(p)) {
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
            response.sendRedirect("start#!/pointage/"+p.getIdpointage());
        }
    }

    public boolean verifierAvantSuppression(Pointage p) {
        return true;
    }
}
