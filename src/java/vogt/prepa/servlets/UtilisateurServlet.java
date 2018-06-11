package vogt.prepa.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.UtilisateurDAO;
import vogt.prepa.dao.UtilisateurProfilDAO;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.entities.UtilisateurProfil;
import vogt.prepa.utils.Notification;

@WebServlet(name = "UtilisateurServlet", urlPatterns = {"/UtilisateurServlet"})
public class UtilisateurServlet extends HttpServlet {

    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    IndividuDAO individuDAO = new IndividuDAO();
    UtilisateurProfilDAO utilisateurProfilDAO = new UtilisateurProfilDAO();

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
                request.setAttribute("individus", individuDAO.getall());
                request.setAttribute("utilisateurProfils", utilisateurProfilDAO.getall());

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/utilisateur.jsp")
                    .forward(request, response);
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
        System.out.print("Action : " + action);
        //Pour supprimer l'entité
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Utilisateur u = utilisateurDAO.get(id);
                if (verifierAvantSuppression(u)) {
                    //utilisateurDAO.supprimer(u);
                    Notification notif = new Notification();
                    notif.setTitre("Suppression");
                    notif.setMessage("L'élement a bien été supprimé");
                    notif.setSuccess(true);
                    notifications.add(notif);
                    httpSession.setAttribute("notifications", notifications);
                    response.sendRedirect("start#!/utilisateurs");
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
            Utilisateur u = null;
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                u = utilisateurDAO.get(i);
            } else {
                u = new Utilisateur();
            }
            String login = request.getParameter("login");
            String passe = request.getParameter("passe");
            String idutilisateurProfil = request.getParameter("profil");
            UtilisateurProfil profil = utilisateurProfilDAO.get(idutilisateurProfil);
            String idindividu = request.getParameter("individu");
            Individu individu = individuDAO.get(idindividu);

            u.setLogin(login);
            u.setPasse(passe);
            u.setIndividu(individu);
            u.setUtilisateurProfil(profil);

            if (utilisateurDAO.enregistrer(u)) {
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
            response.sendRedirect("start#!/utilisateur/"+u.getIdutilisateur());
        }
    }

    public boolean verifierAvantSuppression(Utilisateur u) {
        return true;
    }
}
