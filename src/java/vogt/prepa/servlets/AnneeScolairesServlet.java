package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.AnneeScolaireDAO;
import vogt.prepa.entities.Utilisateur;

@WebServlet(name="AnneeScolairesServlet", urlPatterns={"/AnneeScolairesServlet"})
public class AnneeScolairesServlet extends HttpServlet {

    AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        HttpSession httpSession = request.getSession();
//        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
//        if (utilisateur != null) {
//            request.setAttribute("anneeScolaires", anneeScolaireDAO.getall());
//        }else{
//            response.sendRedirect("index.htm");
//        }
            this.getServletContext().getRequestDispatcher("/newjsp.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
