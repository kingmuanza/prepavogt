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
import vogt.prepa.dao.NiveauEtudeDAO;
import vogt.prepa.entities.NiveauEtude;
import vogt.prepa.entities.Utilisateur;

/**
 *
 * @author zos hall
 */
@WebServlet(name = "NiveauEtudeServlet", urlPatterns = {"/NiveauEtudeServlet"})
public class NiveauEtudeServlet extends HttpServlet {

    NiveauEtudeDAO niveauEtudeDAO = new NiveauEtudeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                NiveauEtude niveauEtude = niveauEtudeDAO.get(i);
                request.setAttribute("niveauEtude", niveauEtude);

            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/niveau.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.htm");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}