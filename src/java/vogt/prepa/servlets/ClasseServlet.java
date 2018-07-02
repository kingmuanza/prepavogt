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
import vogt.prepa.dao.ClasseDAO;
import vogt.prepa.dao.NiveauEtudeDAO;
import vogt.prepa.dao.FiliereDAO;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "ClasseServlet", urlPatterns = {"/ClasseServlet"})
public class ClasseServlet extends HttpServlet {
    ClasseDAO classeDAO = new ClasseDAO();
    FiliereDAO filiereDAO = new FiliereDAO();
    NiveauEtudeDAO niveauDAO = new NiveauEtudeDAO();
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Classe classe = classeDAO.get(i);
                request.setAttribute("classe", classe);

            }
            request.setAttribute("niveaux", niveauDAO.getall());
            request.setAttribute("filieres", filiereDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/classe.jsp").forward(request, response);
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
            System.out.print("ID : " + id);
            if (id != null && !id.isEmpty()) {
                Classe c = classeDAO.get(id);
                if (verifierAvantSuppression(c)) {
                    classeDAO.supprimer(c);
                    System.out.println("Suppression reussie!");
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/classes");
                } else {
                    System.out.println("Suppression echoue!");
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
            Classe c = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                c = classeDAO.get(i);
            } else {
                c = new Classe();
            }
            String niveau = request.getParameter("niveau");
            c.setNiveauEtude(niveauDAO.get(niveau));
            String filiere = request.getParameter("filiere");
            c.setFiliere(filiereDAO.get(filiere));
            
            
            if (classeDAO.enregistrer(c)) {
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
            response.sendRedirect("start#!/classe/"+c.getIdclasse());
        }
    }

    public boolean verifierAvantSuppression(Classe c) {
        return true;
    }

}
