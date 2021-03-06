/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PointageDAO;
import vogt.prepa.entities.Pointage;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.services.impl.EdgarServiceImpl;
import vogt.prepa.services.impl.MuanzaServiceImpl;

@WebServlet(name="ArriveesServlet", urlPatterns={"/ArriveesServlet"})
public class ArriveesServlet extends HttpServlet {
   
    PointageDAO pointageDAO = new PointageDAO();
    MuanzaServiceImpl muanzaService = new MuanzaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IndividuDAO individuDAO = new IndividuDAO();
        MuanzaServiceImpl muanzaServiceImpl = new MuanzaServiceImpl();
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (utilisateur != null) {
            
            List<Pointage> pointages = pointageDAO.getall();
            request.setAttribute("individus", individuDAO.getAllLazy());
            request.setAttribute("individuDAO", individuDAO);
            request.setAttribute("dates", muanzaService.listeDesDatesDePointages(pointages));
            String dateJour = request.getParameter("dateJour");
            if (dateJour != null && !dateJour.isEmpty()) {
                Date d = new Date();
                try {
                    d = sdf.parse(dateJour);
                    request.setAttribute("pointages", muanzaService.premiersPointagesDuJour());
                    request.setAttribute("jour", dateJour);
                    System.out.println("Bonne date alors : " + d);
                } catch (ParseException ex) {
                    System.out.println("Erreur parssing de date !!!");
                }
            }
            request.setAttribute("muanzaServiceImpl", muanzaServiceImpl);
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/arrivees.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
}
