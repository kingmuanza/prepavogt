package vogt.prepa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.UtilisateurDAO;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

@WebServlet(name = "ConnexionServlet", urlPatterns = {"/ConnexionServlet"})
public class ConnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String passe = request.getParameter("passe");
        System.out.println("Login :" + login);
        System.out.println("Mot de Passe :" + passe);

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        Utilisateur utilisateur = utilisateurDAO.getUserLoginInformations(login, passe);

        if (utilisateur != null) {
            List<Notification> notifications = new ArrayList<>();
//            Notification notif = new Notification();
//            notif.setTitre("Connexion");
//            notif.setMessage("Vous venez de vous connecter !");
//            notif.setSuccess(true);
//            notifications.add(notif);
//            Notification notif2 = new Notification();
//            notif2.setTitre("Pointeuse");
//            notif2.setMessage("Echec de connexion à la pointeuse biométrique !");
//            notif2.setSuccess(false);
//            notifications.add(notif2);

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("utilisateur", utilisateur);
            httpSession.setAttribute("notifications", notifications);

            System.out.println(utilisateur.getIdutilisateur());

            response.sendRedirect("start");
        }else{
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("connexionError", "Login ou mot de passe incorrect");
            response.sendRedirect("index.htm");
        }

    }

}
