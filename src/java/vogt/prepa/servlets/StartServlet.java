package vogt.prepa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.EntreeDAO;
import vogt.prepa.dao.VisiteDAO;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

@WebServlet(name="StartServlet", urlPatterns={"/start"})
public class StartServlet extends HttpServlet {
    
    VisiteDAO visiteDAO = new VisiteDAO();
    EntreeDAO entreeDAO = new EntreeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        List<Notification> notifications = (List<Notification>) httpSession.getAttribute("notifications");
        if (utilisateur != null) {
            httpSession.removeAttribute("connexionError");
            httpSession.setAttribute("notifications", new ArrayList<>());
            request.setAttribute("visites", visiteDAO.getall());
            request.setAttribute("entrees", entreeDAO.getall());
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/start.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
