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
import java.util.List;
import java.util.Map;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.EdgarService;

/**
 *
 * @author 12Lions
 */
public class EdgarServiceImpl implements EdgarService {

    @Override
    public String ExtraireHeure(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String heure = dateFormat.format(date);
        return heure;
    }

    @Override
    public Date getDebutdeJournee(Date journee) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(journee);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }

    @Override
    public Date getFindeJournee(Date journee) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(journee);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.HOUR, 11);

        return calendar.getTime();
    }

    @Override
    public boolean ComparerHeure(String heure1, String heure2) {
        boolean superieur = false;
        if ((heure1.compareTo(heure2) > 0) || (heure1.compareTo(heure2) == 0)) {
            superieur = true;
        }
        return superieur;
    }

    @Override
    public boolean JourDeClasse(Date dateDuJour) {
        boolean dim = true;
        String nomJour = new SimpleDateFormat("EEEE").format(dateDuJour);
        if (nomJour.equals("dimanche")){
            dim = false;
        }        
        return dim;
    }

    @Override
    public List<Pointage> PointagesDUnJour(Date dateDuJour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDates(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> PointagesDUnJourPourUnMatricule(String matricule, Date dateDuJour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDatesPourUnMatricule(String matricule, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> PointagesDUnJourPourUnIndividu(Individu individu, Date dateDuJour) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDatesPourUnIndividu(Individu individu, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pointage premierPointage(String matricule, Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pointage premiersPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pointage premierPointage(Individu individu, Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pointage premiersPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estEnRetard(Individu individu, Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> retardsPointagesDUnJourLa(Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> retardsMatriculeDUnJourLa(Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Individu> retardsIndividuDUnJourLa(Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> retardsMatriculeEntreDeuxDates(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Individu> retardsIndividuEntreDeuxDates(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long cummulDesRetards(String matricule, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long cummulDesRetards(Individu individu, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean estAbsent(Individu individu, Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> matriculesAbsentsUnJourLa(Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Individu> individusAbsentsUnJourLa(Date jourLa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Date> joursAbsencesMatricule(String matricule) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Date> joursAbsencesIndividu(Individu individu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nombreAbsencesMatricule(String matricule, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nombreAbsencesIndividu(Individu individu, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, List<Date>> AbsencesMatricules(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Individu, List<Date>> AbsencesIndividus(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Integer> nombreAbsencesMatricules(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Individu, Integer> nombreAbsencesIndividus(Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
