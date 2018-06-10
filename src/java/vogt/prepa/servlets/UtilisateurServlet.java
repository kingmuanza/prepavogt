package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.UtilisateurDAO;
import vogt.prepa.dao.UtilisateurProfilDAO;
import vogt.prepa.entities.Utilisateur;

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
                int i = Integer.parseInt(id);
                Utilisateur u = utilisateurDAO.get(i);
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
        String action = request.getParameter("action");
        //Pour supprimer l'entité
        if (action != null && !action.isEmpty() && "supprimer".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                Utilisateur u = utilisateurDAO.get(i);
                if (verifierAvantSuppression(u)) {
                    utilisateurDAO.supprimer(u);
                    response.sendRedirect("start#!/utilisateurs");
                } else {

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
            String confirmation = request.getParameter("confirmation");
            
            u.setLogin(login);
            u.setPasse(passe);

        }
    }

    public boolean verifierAvantSuppression(Utilisateur u) {
        return true;
    }
}
