package vogt.prepa.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vogt.prepa.dao.ClasseDAO;

@WebServlet(name="AujourdhuiServlet", urlPatterns={"/AujourdhuiServlet"})
public class AujourdhuiServlet extends HttpServlet {
   
    ClasseDAO classeDAO = new ClasseDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setAttribute("classes", classeDAO.getall());
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/aujourdhui.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
