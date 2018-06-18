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
import vogt.prepa.dao.EnseignantDAO;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "EnseignantServlet", urlPatterns = {"/EnseignantServlet"})
public class EnseignantServlet extends HttpServlet {

    EnseignantDAO enseignantDAO = new EnseignantDAO();
    IndividuDAO individuDAO = new IndividuDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Enseignant enseignant = enseignantDAO.get(i);
                request.setAttribute("enseignant", enseignant);

            }
            request.setAttribute("individus", individuDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enseignant.jsp").forward(request, response);
        }else{
//            response.sendRedirect("index.htm");
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
                Enseignant i = enseignantDAO.get(id);
                if (verifierAvantSuppression(i)) {
                    enseignantDAO.supprimer(i);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/enseignants");
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
            Enseignant ens = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                ens = enseignantDAO.get(i);
            } else {
                ens = new Enseignant();
            }
            
            String individu = request.getParameter("individu");
            if (!individu.equals("Aucun individu")) {
                ens.setIndividu(individuDAO.get(individu));
            } else {
                Individu ind = new Individu();
                String civilite = request.getParameter("civilite");
                ind.setCivilite(civilite);
                String dateNaissance = request.getParameter("dateNaissance");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    ind.setDatenaiss(sdf.parse(dateNaissance));
                } catch (ParseException ex) {
                    ind.setDatenaiss(new Date());
                }
                String email = request.getParameter("email");
                ind.setEmail(email);
                String genre = request.getParameter("genre");
                ind.setGenre(Boolean.parseBoolean(genre));
                String lieuNaissance = request.getParameter("lieuNaissance");
                ind.setLieunaiss(lieuNaissance);
                String matricule = request.getParameter("matricule");
                ind.setMatricule(matricule);
                String noms = request.getParameter("noms");
                ind.setNoms(noms);
                String prenoms = request.getParameter("prenoms");
                ind.setPrenoms(prenoms);
                String residence = request.getParameter("residence");
                ind.setResidence(residence);
                String telephone1 = request.getParameter("telephone1");
                ind.setTel1(telephone1);
                String telephone2 = request.getParameter("telephone2");
                ind.setTel2(telephone2);
                individuDAO.enregistrer(ind);
                ens.setIndividu(ind);
            }
            

            if (enseignantDAO.enregistrer(ens)) {
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
            response.sendRedirect("start#!/enseignant/" + ens.getIdenseignant());
        }
    }
    
    public boolean verifierAvantSuppression(Enseignant i) {
        return true;
    }
}
