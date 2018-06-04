package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.NiveauEtudeDAO;
import vogt.prepa.entities.Utilisateur;

@WebServlet(name="NiveauEtudesServlet", urlPatterns={"/NiveauEtudesServlet"})
public class NiveauEtudesServlet extends HttpServlet {

    NiveauEtudeDAO niveauEtudeDAO = new NiveauEtudeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            request.setAttribute("niveauEtudes", niveauEtudeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/niveaux.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
