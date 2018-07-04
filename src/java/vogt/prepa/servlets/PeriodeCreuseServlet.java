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
import vogt.prepa.dao.PeriodeCreuseDAO;
import vogt.prepa.entities.PeriodeCreuse;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "PeriodeCreuseServlet", urlPatterns = {"/PeriodeCreuseServlet"})
public class PeriodeCreuseServlet extends HttpServlet {

    PeriodeCreuseDAO periodeCreuseDAO = new PeriodeCreuseDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                PeriodeCreuse periodeCreuse = periodeCreuseDAO.get(i);
                request.setAttribute("periodeCreuse", periodeCreuse);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/periodecreuse.jsp").forward(request, response);
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
            if (id != null && !id.isEmpty()) {
                PeriodeCreuse pCreuse = periodeCreuseDAO.get(id);
                if (verifierAvantSuppression(pCreuse)) {
                    periodeCreuseDAO.supprimer(pCreuse);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/periodescreuses");
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
            PeriodeCreuse pCreuse = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                pCreuse = periodeCreuseDAO.get(i);
            } else {
                pCreuse = new PeriodeCreuse();
            }
            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String debut = request.getParameter("dateDebut");
            String fin = request.getParameter("dateFin");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = null;
            Date dateFin = null;
            try {
                dateDebut = sdf.parse(debut);
                
            } catch (ParseException el) {
                //System.out.println("la date entrée est fausse, retapez la date");
            }
            
            try {
                dateFin = sdf.parse(fin);
            } catch (ParseException el) {
                //System.out.println("la date final entrée est fausse, retapez la date");
            }
            
            pCreuse.setCode(code);
            pCreuse.setLibelle(libelle);
            pCreuse.setDateDebut(dateDebut);
            pCreuse.setDateFin(dateFin);
            if (periodeCreuseDAO.enregistrer(pCreuse)) {
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
            response.sendRedirect("start#!/periodecreuse/"+pCreuse.getIdperiodeCreuse());
        }
    }

    public boolean verifierAvantSuppression(PeriodeCreuse periodeCreuse) {
        return true;
    }
}
