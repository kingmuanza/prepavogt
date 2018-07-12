package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PointageDAO;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.impl.TempsReelService;

@WebServlet(name = "ActualiserPointageServlet", urlPatterns = {"/ActualiserPointageServlet"})
public class ActualiserPointageServlet extends HttpServlet {

    TempsReelService tempsReelService = new TempsReelService();

    String s = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        IndividuDAO individuDAO = new IndividuDAO(); 
        PointageDAO pointageDAO = new PointageDAO();

        String paragraphe = "";

        for (Pointage p : tempsReelService.getDerniersPointages()) {
            Individu individu = individuDAO.getByMatricule(p.getMatricule());
            pointageDAO.enregistrer(p);
            String nom = "";
            if(individu!=null){
                nom = individu.getNoms()+" "+individu.getPrenoms();
            }
            String e = "{"
                    + "\"heure\":\"" + dateFormat.format(p.getHeure()) + "\","
                    + "\"matricule\":\"" + p.getMatricule() + "\","
                    + "\"noms\":\"" + nom + "\","
                    + "\"entree\":" + "true"
                    + "}";
            if (paragraphe == "") {
                paragraphe = paragraphe + e;
            } else {
                paragraphe = paragraphe + ", " + e;
            }
        }

        PrintWriter pw = response.getWriter();
        pw.println("[" + paragraphe + "]");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
