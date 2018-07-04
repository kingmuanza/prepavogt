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
import vogt.prepa.dao.NiveauEtudeDAO;
import vogt.prepa.entities.NiveauEtude;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "NiveauEtudeServlet", urlPatterns = {"/NiveauEtudeServlet"})
public class NiveauEtudeServlet extends HttpServlet {

    NiveauEtudeDAO niveauEtudeDAO = new NiveauEtudeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                NiveauEtude niveauEtude = niveauEtudeDAO.get(i);
                request.setAttribute("niveauEtude", niveauEtude);
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/niveau.jsp").forward(request, response);
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
        //System.out.print("Action : " + action);
        //Pour supprimer l'entité
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                NiveauEtude niv = niveauEtudeDAO.get(id);
                if (verifierAvantSuppression(niv)) {
                    niveauEtudeDAO.supprimer(niv);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/niveaux");
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
            NiveauEtude niv = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                niv = niveauEtudeDAO.get(i);
            } else {
                niv = new NiveauEtude();
            }
            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String valeure = request.getParameter("valeur");
            int valeur;
            try{
                valeur = Integer.parseInt(valeure);
                niv.setValeur(valeur);
            }catch(NumberFormatException nfe){
                //System.out.println(nfe);
            }
            niv.setCode(code);
            niv.setLibelle(libelle);
            

            if (niveauEtudeDAO.enregistrer(niv)) {
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
            response.sendRedirect("start#!/niveau/" + niv.getIdniveauEtude());
        }
    }

    public boolean verifierAvantSuppression(NiveauEtude niv) {
        return true;
    }
}
