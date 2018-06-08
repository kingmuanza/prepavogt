/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 12Lions
 */
public class EdgarServiceImplTest {

    //Main
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(JourDeClasse(date));

//        String heure1 = "15:00:00";
//        String heure2 = "15:00:00";        
//        System.out.println(ComparerHeure(heure1,heure2));
    }

    //ExtraireHeure
    public static String extraireHeure(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String heure = dateFormat.format(date);
        return heure;
    }

    //getDebutdeJournee
    public static Date getDebutdeJournee(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);

        return calendar.getTime();
    }

    //getDebutdeJournee
    public static Date getFindeJournee(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR, 11);

        return calendar.getTime();
    }

    //getDebutdeJournee
    public static boolean ComparerHeure(String heure1, String heure2) {
        boolean superieur = false;
        if ((heure1.compareTo(heure2) > 0) || (heure1.compareTo(heure2) == 0)) {
            superieur = true;
        }
        return superieur;
    }

    //JourDeClasse
    public static boolean JourDeClasse(Date dateDuJour) {
        boolean dim = false;
        String nomJour = new SimpleDateFormat("EEEE").format(dateDuJour);
        if (nomJour.equals("dimanche")) {
            dim = true;
        }
        return dim;
    }
}
