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
import vogt.prepa.dao.CoursDAO;
import vogt.prepa.dao.FiliereDAO;
import vogt.prepa.dao.MatiereDAO;
import vogt.prepa.dao.NiveauEtudeDAO;
import vogt.prepa.entities.Cours;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.Matiere;
import vogt.prepa.entities.NiveauEtude;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "CoursServlet", urlPatterns = {"/CoursServlet"})
public class CoursServlet extends HttpServlet {

    CoursDAO coursDAO = new CoursDAO();
    FiliereDAO filiereDAO = new FiliereDAO();
    MatiereDAO matiereDAO = new MatiereDAO();
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
                Cours cours = coursDAO.get(i);
                request.setAttribute("cours", cours);
            }
            request.setAttribute("filieres", filiereDAO.getall());
            request.setAttribute("matieres", matiereDAO.getall());
            request.setAttribute("niveauEtudes", niveauEtudeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/cours.jsp").forward(request, response);
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
        if (action != null && !action.isEmpty() && "supprimer".equals(action)){
            String id = request.getParameter("id");
            System.out.print("ID : " + id);
            if (id != null && !id.isEmpty()) {
                Cours c = coursDAO.get(id);
                if (verifierAvantSuppression(c)) {
                    coursDAO.supprimer(c);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/coursall");
                } else {
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("Echec de suppression !");
                    notif.setSuccess(false);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                }
            }
        }//Pour ajouter ou modifier l'entité
        else {
            String id = request.getParameter("id");
            Cours c = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                c = coursDAO.get(i);
            } else {
                c = new Cours();
                
            }
            String idfiliere = request.getParameter("filiere");
            String idmatiere = request.getParameter("matiere");
            String idniveauEtude = request.getParameter("niveauEtude");
            Filiere filiere = filiereDAO.get(idfiliere);
            Matiere matiere = matiereDAO.get(idmatiere);
            NiveauEtude niveauEtude = niveauEtudeDAO.get(idniveauEtude);
            
            c.setFiliere(filiere);
            c.setMatiere(matiere);
            c.setNiveauEtude(niveauEtude);

            if (coursDAO.enregistrer(c)) {
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
            response.sendRedirect("start#!/cours/"+c.getIdcours());
        }
    }

    public boolean verifierAvantSuppression(Cours c) {
        return true;
    }
 
}
