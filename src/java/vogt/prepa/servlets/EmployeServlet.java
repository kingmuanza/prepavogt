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
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.EmployeDAO;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PosteDAO;
import vogt.prepa.entities.Employe;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Poste;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "EmployeServlet", urlPatterns = {"/EmployeServlet"})
public class EmployeServlet extends HttpServlet {

    EmployeDAO employeDAO = new EmployeDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    PosteDAO posteDAO = new PosteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Employe employe = employeDAO.get(i);
                request.setAttribute("employe", employe);

            }
            request.setAttribute("postes", posteDAO.getall());
            request.setAttribute("individus", individuDAO.getall());
            Individu ind = (Individu) httpSession.getAttribute("nouvelIndividu");
            if (ind != null) {
                request.setAttribute("nouvelIndividu", ind);
            }
            if ((Integer) httpSession.getAttribute("countDown") != null && (Integer) httpSession.getAttribute("countDown") == 1) {
                httpSession.setAttribute("nouvelIndividu", null);
            } else {
                httpSession.setAttribute("countDown", 1);
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/collaborateur.jsp").forward(request, response);
        } else {
//            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("On tente quelquechose en studio !");
        HttpSession httpSession = request.getSession();
        List<Notification> notifications = (List<Notification>) httpSession.getAttribute("notifications");
        //pur créer un individu depuis le formulaire de la fenetre modale
        String newIndividu = request.getParameter("newIndividu");
        if (newIndividu != null && !newIndividu.isEmpty()) {
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
            boolean teste = individuDAO.enregistrer(ind);
            if (true) {
                httpSession.setAttribute("nouvelIndividu", ind);
                httpSession.setAttribute("countDown", 2);
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
            System.out.println("On a tenté quelquechose en studio !");
            PrintWriter pw = response.getWriter();
            String e = "{"
                    + "\"id\":\"" + ind.getIdindividu() + "\","
                    + "\"noms\":\"" + ind.getNoms() + "\","
                    + "\"prenoms\":\"" + ind.getPrenoms() + "\""
                    + "}";
            pw.println(e);

        } else {
            String action = request.getParameter("action");
            //Pour supprimer l'entité
            if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) {
                    Employe e = employeDAO.get(id);
                    if (verifierAvantSuppression(e)) {
                        employeDAO.supprimer(e);
                        Notification notif = new Notification();
                        notif.setTitre("Suppression");
                        notif.setMessage("L'élement a bien été supprimé");
                        notif.setSuccess(true);
                        notifications.add(notif);
                        httpSession.setAttribute("notifications", notifications);
                        response.sendRedirect("start#!/collaborateurs");
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
                Employe e = null;
                if (id != null && !id.isEmpty()) {
                    int i = Integer.parseInt(id);
                    e = employeDAO.get(i);
                } else {
                    e = new Employe();

                }
                String poste = request.getParameter("poste");
                e.setPoste(posteDAO.get(poste));

                String individu = request.getParameter("individu");
                if (!individu.equals("Aucun individu")) {
                    e.setIndividu(individuDAO.get(individu));
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
                    e.setIndividu(ind);
                }

                if (employeDAO.enregistrer(e)) {
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
                response.sendRedirect("start#!/collaborateur/" + e.getIdemploye());
            }
        }

    }

    public boolean verifierAvantSuppression(Employe e) {
        return true;
    }

}
