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
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.Notification;

@WebServlet(name="StartServlet", urlPatterns={"/start"})
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        List<Notification> notifications = (List<Notification>) httpSession.getAttribute("notifications");
        if (utilisateur != null) {
            request.setAttribute("notifications", notifications);
            httpSession.setAttribute("notifications", new ArrayList<>());
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
