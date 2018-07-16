/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vogt.prepa.dao.ClasseDAO;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.services.impl.EdgarServiceImpl;
import vogt.prepa.services.impl.TonyServiceImpl;
import vogt.prepa.utils.Statistique;

@WebServlet(name = "AujourdhuiClasseServlet", urlPatterns = {"/AujourdhuiClasseServlet"})
public class AujourdhuiClasseServlet extends HttpServlet {

    ClasseDAO classeDAO = new ClasseDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        EdgarServiceImpl edgarServiceImpl = new EdgarServiceImpl();
        TonyServiceImpl tonyServiceImpl = new TonyServiceImpl();

        if (utilisateur != null) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                Classe classe = classeDAO.get(id);
                request.setAttribute("classe", classe);
                request.setAttribute("edgarServiceImpl", edgarServiceImpl);
                Statistique stat = tonyServiceImpl.statistiques(classe, new Date());
                request.setAttribute("stat", stat);
                request.setAttribute("jourLa", new Date());
                request.setAttribute("huit", huitheure());

            }
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/aujourdhuiclasse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public Date huitheure() {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 22); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date d1 = c.getTime();
        return d1;
    }

}
