package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.UtilisateurProfilClasseDAO;
import vogt.prepa.entities.Utilisateur;

@WebServlet(name="UtilisateurProfilFilieresServlet", urlPatterns={"/UtilisateurProfilFilieresServlet"})
public class UtilisateurProfilFilieresServlet extends HttpServlet {

    UtilisateurProfilClasseDAO utilisateurProfilFiliereDAO = new UtilisateurProfilClasseDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            request.setAttribute("utilisateurProfilFilieres", utilisateurProfilFiliereDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/UtilisateurProfilFilieresServlet.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
