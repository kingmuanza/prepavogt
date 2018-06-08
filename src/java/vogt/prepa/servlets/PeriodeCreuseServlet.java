/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.PeriodeCreuseDAO;
import vogt.prepa.entities.PeriodeCreuse;
import vogt.prepa.entities.Utilisateur;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "PeriodeCreuseServlet", urlPatterns = {"/PeriodeCreuseServlet"})
public class PeriodeCreuseServlet extends HttpServlet {

    PeriodeCreuseDAO periodeCreuseDAO = new PeriodeCreuseDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                PeriodeCreuse periodeCreuse = periodeCreuseDAO.get(i);
                request.setAttribute("periodeCreuse", periodeCreuse);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/periodescreuse.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
}
