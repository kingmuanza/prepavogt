/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 12Lions
 */
public class EdgarServiceImplTest {

    //Main
    public static void main(String[] args) {
//        String timeString1 = "08:00:00";
//        String timeString2 = "13:30:50";
//        
//        //System.out.println(getDiffHeureMinutes(timeString1, timeString2));
//        
//        int a = getDiffHeureMinutes(timeString1, timeString2).get(0) + 10;
//        //System.out.println(a);
        List<Long> difference = new ArrayList<>();
        difference.add(1L);
        //System.out.println(difference.get(0));
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
        boolean dim = true;
        String nomJour = new SimpleDateFormat("EEEE").format(dateDuJour);
        if (nomJour.equals("dimanche")) {
            dim = false;
        }
        return dim;
    }

    public static List<Integer> getDiffHeureMinutes(String heure1, String heure2) {
        List<Integer> diffheureEtMinutes = new ArrayList<>();

        String[] fractions1 = heure1.split(":");
        String[] fractions2 = heure2.split(":");
        Integer heureDebut = Integer.parseInt(fractions1[0]);
        Integer heureFin = Integer.parseInt(fractions2[0]);
        Integer minutesDebut = Integer.parseInt(fractions1[1]);
        Integer minutesFin = Integer.parseInt(fractions2[1]);
        int heureDiff = heureFin - heureDebut;
        int minutesDiff = minutesFin - minutesDebut;

        if (minutesDiff < 0) {
            minutesDiff = 60 + minutesDiff;
            heureDiff--;
        }
        if (heureDiff < 0) {
            heureDiff = 24 + heureDiff;
        }

        diffheureEtMinutes.add(heureDiff);
        diffheureEtMinutes.add(minutesDiff);

        return diffheureEtMinutes;
    }

//    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
//        long diffInMillies = date2.getTime() - date1.getTime();
//        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
//    }
}
