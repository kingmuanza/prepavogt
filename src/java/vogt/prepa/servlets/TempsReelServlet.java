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
import javax.servlet.http.HttpSession;
import vogt.prepa.entities.Pointage;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.services.impl.TempsReelService;

@WebServlet(name="TempsReelServlet", urlPatterns={"/TempsReelServlet"})
public class TempsReelServlet extends HttpServlet {

    TempsReelService tempsReelService = new TempsReelService();  
    List<Pointage> pointages = tempsReelService.getDerniersPointages();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        
        List<Pointage> ps = new ArrayList<Pointage>();
        ps.addAll(pointages);
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            request.setAttribute("pointages", ps);
            request.setAttribute("temps", new Date().getTime());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tempsReel.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
