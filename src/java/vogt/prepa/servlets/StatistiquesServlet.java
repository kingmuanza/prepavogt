package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PointageDAO;

@WebServlet(name = "StatistiquesServlet", urlPatterns = {"/StatistiquesServlet"})
public class StatistiquesServlet extends HttpServlet {

    PointageDAO pointageDAO = new PointageDAO();
    IndividuDAO individuDAO = new IndividuDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("collaborateur".equals(type)) {
            request.setAttribute("plus", "des collaborateurs");
            request.setAttribute("pointages", pointageDAO.getEmployePointages());
        }
        if ("enseignant".equals(type)) {
            request.setAttribute("plus", "des enseignants");
            request.setAttribute("pointages", pointageDAO.getEnseignantPointages());
        }
        if ("etudiant".equals(type)) {
            request.setAttribute("plus", "des étudiants");
            request.setAttribute("pointages", pointageDAO.getEtudiantPointages());
        }
        request.setAttribute("individus", individuDAO.getAllLazy());
        request.setAttribute("individuDAO", individuDAO);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/statistiques.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
