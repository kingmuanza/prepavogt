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
import vogt.prepa.dao.AnneeScolaireDAO;
import vogt.prepa.entities.Utilisateur;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "AnneeScolaireServlet", urlPatterns = {"/AnneeScolaireServlet"})
public class AnneeScolaireServlet extends HttpServlet {

    AnneeScolaireDAO anneeScolaireDAO = new AnneeScolaireDAO();
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
//            request.setAttribute("anneeScolaires", anneeScolaireDAO.getall());
//            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/anneeacademique.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
            
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
