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
import vogt.prepa.dao.VisiteCategorieDAO;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.entities.Visite;
import vogt.prepa.entities.VisiteCategorie;

@WebServlet(name="CategoriesServlet", urlPatterns={"/CategoriesServlet"})
public class CategoriesServlet extends HttpServlet {
   
    VisiteCategorieDAO visiteCategorieDAO = new VisiteCategorieDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                VisiteCategorie visiteCategorie = visiteCategorieDAO.get(id);
                request.setAttribute("visites", visiteCategorie.getVisites());
                request.setAttribute("libelle", visiteCategorie.getLibelle());
            }
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/visites.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.htm");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
}
