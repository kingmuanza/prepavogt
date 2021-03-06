package vogt.prepa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vogt.prepa.dao.ClasseDAO;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.impl.EdgarServiceImpl;
import vogt.prepa.services.impl.TonyServiceImpl;
import vogt.prepa.utils.Statistique;

@WebServlet(name="AujourdhuiServlet", urlPatterns={"/AujourdhuiServlet"})
public class AujourdhuiServlet extends HttpServlet {
   
    ClasseDAO classeDAO = new ClasseDAO();
    TonyServiceImpl tonyServiceImpl = new TonyServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<Statistique> statistiques = new ArrayList<Statistique>();
        for(Classe c : classeDAO.getall()){
            statistiques.add(tonyServiceImpl.statistiques(c, new Date()));
        }
        request.setAttribute("classes", classeDAO.getall());
        request.setAttribute("statistiques", statistiques);
        request.setAttribute("aujourdhui", new Date());
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/aujourdhui.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
