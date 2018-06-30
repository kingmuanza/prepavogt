package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PointageDAO;
import vogt.prepa.entities.Utilisateur;

@WebServlet(name="PointagesServlet", urlPatterns={"/PointagesServlet"})
public class PointagesServlet extends HttpServlet {

    PointageDAO pointageDAO = new PointageDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        IndividuDAO individuDAO = new IndividuDAO(); 
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            request.setAttribute("pointages", pointageDAO.getall());
            request.setAttribute("individuDAO", individuDAO);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pointages.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
