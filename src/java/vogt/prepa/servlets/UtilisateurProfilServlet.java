/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.ClasseDAO;
import vogt.prepa.dao.FiliereDAO;
import vogt.prepa.dao.UtilisateurProfilDAO;
import vogt.prepa.dao.UtilisateurProfilClasseDAO;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.entities.UtilisateurProfil;
import vogt.prepa.entities.UtilisateurProfilClasse;
import vogt.prepa.utils.FiliereUtil;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "UtilisateurProfilServlet", urlPatterns = {"/UtilisateurProfilServlet"})
public class UtilisateurProfilServlet extends HttpServlet {

    UtilisateurProfilDAO utilisateurProfilDAO = new UtilisateurProfilDAO();
    ClasseDAO classeDAO = new ClasseDAO();
    UtilisateurProfilClasseDAO utilisateurProfilClasseDAO = new UtilisateurProfilClasseDAO();
    FiliereUtil filiereUtil = new FiliereUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                UtilisateurProfil utilisateurProfil = utilisateurProfilDAO.get(id);
                request.setAttribute("utilisateurProfil", utilisateurProfil);
                request.setAttribute("filiereUtil", filiereUtil);

            }
                request.setAttribute("classes", classeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
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
                UtilisateurProfil utilProfil = utilisateurProfilDAO.get(id);
                if (verifierAvantSuppression(utilProfil)) {
                    utilisateurProfilDAO.supprimer(utilProfil);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/profils");
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
            UtilisateurProfil utilProfil = null;
            if (id != null && !id.isEmpty()) {
//                int i = Integer.parseInt(id);
                utilProfil = utilisateurProfilDAO.get(id);
            } else {
                utilProfil = new UtilisateurProfil();
            }

            String code = request.getParameter("code");
            String libelle = request.getParameter("libelle");
            String voirEmp = request.getParameter("employe");
            String voirEns = request.getParameter("enseignant");

            utilProfil.setCode(code);
            utilProfil.setLibelle(libelle);
            try {
                boolean empl = Boolean.parseBoolean(voirEmp);
                utilProfil.setVoirEmploye(empl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                boolean ens = Boolean.parseBoolean(voirEns);
                utilProfil.setVoirEnseignant(ens);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (utilisateurProfilDAO.enregistrer(utilProfil)) {
                Notification notif = new Notification();
                notif.setTitre("Enregistrement");
                notif.setMessage("L'élement a bien été enregistré");
                notif.setSuccess(true);
                notifications.add(notif);
                httpSession.setAttribute("notifications", notifications);
                String[] classes = request.getParameterValues("classes");
                //System.out.println(classes.toString());
                deleteFilieres(utilProfil);
                SaveFilieres(utilProfil, classes);
            } else {
                Notification notif = new Notification();
                notif.setTitre("Enregistrement");
                notif.setMessage("Echec d'enregistrement");
                notif.setSuccess(true);
                notifications.add(notif);
                httpSession.setAttribute("notifications", notifications);
            }

            response.sendRedirect("start#!/profil/" + utilProfil.getIdutilisateurProfil());
        }
    }

    public boolean verifierAvantSuppression(UtilisateurProfil utilProfil) {
        return true;
    }

    public void SaveFilieres(UtilisateurProfil utilProfil, String[] classes) {
        for (String f : classes) {
            Classe classe = classeDAO.get(f);
            UtilisateurProfilClasse upf = new UtilisateurProfilClasse();
            upf.setClasse(classe);
            upf.setUtilisateurProfil(utilProfil);
            utilisateurProfilClasseDAO.enregistrer(upf);
        }
    }
    
    public void deleteFilieres(UtilisateurProfil utilProfil) {
        for (UtilisateurProfilClasse upf : utilProfil.getUtilisateurProfilClasses()) {
            utilisateurProfilClasseDAO.supprimer(upf);
        }
    }
}
