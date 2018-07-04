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
import vogt.prepa.dao.CoursDAO;
import vogt.prepa.dao.CoursEnseignantDAO;
import vogt.prepa.dao.EnseignantDAO;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.entities.Cours;
import vogt.prepa.entities.CoursEnseignant;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.CoursUtil;
import vogt.prepa.utils.Notification;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "EnseignantServlet", urlPatterns = {"/EnseignantServlet"})
public class EnseignantServlet extends HttpServlet {

    EnseignantDAO enseignantDAO = new EnseignantDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    CoursDAO coursDAO = new CoursDAO();
    CoursEnseignantDAO coursEnseignantDAO = new CoursEnseignantDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");

        CoursUtil coursUtil = new CoursUtil();
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Enseignant enseignant = enseignantDAO.get(i);
                request.setAttribute("enseignant", enseignant);

            }
            request.setAttribute("coursUtil", coursUtil);
            request.setAttribute("individus", individuDAO.getall());
            request.setAttribute("courses", coursDAO.getall());

            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/enseignant.jsp").forward(request, response);
        } else {
//            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            if (individuDAO.enregistrer(ind)) {
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
            response.sendRedirect("start#!/" + newIndividu);
            //System.out.println("On a tenté quelquechose en studio !");
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

                }

                if (enseignantDAO.enregistrer(ens)) {
                    Notification notif = new Notification();
                    notif.setTitre("Enregistrement");
                    notif.setMessage("L'élement a bien été enregistré");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    String[] courses = request.getParameterValues("courses");
                    //System.out.println(courses.toString());
                    deleteCourses(ens);
                    SaveCourses(ens, courses);
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
    }

    public boolean verifierAvantSuppression(Enseignant i) {
        return true;
    }

    public void SaveCourses(Enseignant enseignant, String[] courses) {
        for (String f : courses) {
            Cours cours = coursDAO.get(f);
            CoursEnseignant ce = new CoursEnseignant();
            ce.setCours(cours);
            ce.setEnseignant(enseignant);
            coursEnseignantDAO.enregistrer(ce);
        }
    }

    public void deleteCourses(Enseignant enseignant) {
        for (CoursEnseignant ce : enseignant.getCoursEnseignants()) {
            coursEnseignantDAO.supprimer(ce);
        }
    }
}
