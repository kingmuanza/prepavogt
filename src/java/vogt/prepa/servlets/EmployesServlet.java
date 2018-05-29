package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.EmployeDAO;
import vogt.prepa.entities.Utilisateur;

@WebServlet(name="EmployesServlet", urlPatterns={"/EmployesServlet"})
public class EmployesServlet extends HttpServlet {

    EmployeDAO employeDAO = new EmployeDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            request.setAttribute("employes", employeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EmployesServlet.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
