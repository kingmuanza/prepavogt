package vogt.prepa.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.TempsReelService;

@WebServlet(name = "ActualiserPointageServlet", urlPatterns = {"/ActualiserPointageServlet"})
public class ActualiserPointageServlet extends HttpServlet {

    TempsReelService tempsReelService = new TempsReelService();

    String s = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        String paragraphe = "";

        for (Pointage p : tempsReelService.getDerniersPointages()) {
            String e = "{"
                    + "\"heure\":\"" + dateFormat.format(p.getHeure()) + "\","
                    + "\"matricule\":\"" + p.getMatricule() + "\","
                    + "\"noms\":" + "\"Personne inconnue\"" + ","
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
