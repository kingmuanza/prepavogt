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
        List<Pointage> lpointage = new EdgarServiceImpl().PointagesDUnJour(new Date("2017/5/10"));
        for(Classe c : classeDAO.getall()){
            statistiques.add(tonyServiceImpl.statistiques(c, new Date("2017/5/10")));
            System.out.println("Nombre de pointages de la classe : "+ tonyServiceImpl.findEtudiantByClasse(c, lpointage).size());
        }
        request.setAttribute("classes", classeDAO.getall());
        request.setAttribute("statistiques", statistiques);
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/aujourdhui.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
