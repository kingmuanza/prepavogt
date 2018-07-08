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
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.EdgarService;
import vogt.prepa.utils.HibernateUtil;

/**
 *
 * @author 12Lions
 */
public class EdgarServiceImpl implements EdgarService {

    /*
    *   Retoure uniquement la partie heure (En String)
     */
    @Override
    public String ExtraireHeure(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String heure = dateFormat.format(date);
        return heure;
    }

    /*
    *   Retoure uniquement la partie date (En String)
     */
    @Override
    public String ExtraireDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dat = dateFormat.format(date);
        return dat;
    }

    /*
    *   Reset la partie heure à 00h:00m:00s
     */
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

    /*
    *   Modifie la partie heure à 23h:59m:00s
     */
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

    /*
    *  h1 >= h2 ? renvoie true si oui false sinon
     */
    @Override
    public boolean ComparerHeure(String heure1, String heure2) {
        boolean plusGrandouEgal = false;
        if ((heure1.compareTo(heure2) >= 0)) {
            plusGrandouEgal = true;
        }
        return plusGrandouEgal;
    }

    /*
    *  renvoie false si on est dimanche
     */
    @Override
    public boolean JourDeClasse(Date dateDuJour) {
        boolean dim = true;
        String nomJour = new SimpleDateFormat("EEEE").format(dateDuJour);
        if (nomJour.equals("dimanche")) {
            dim = false;
        }
        return dim;
    }

    /*
    *  renvoie la liste des pointage d'un jour precis
     */
    @Override
    public List<Pointage> PointagesDUnJour(Date dateDuJour) {

        Date debutJournee = getDebutdeJournee(dateDuJour);
        Date finJournee = getFindeJournee(dateDuJour);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = session.createCriteria(Pointage.class)
                .add(Restrictions.between("heure", debutJournee, finJournee))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesJour;
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDates(Date dateDebut, Date dateFin) {

        Date commencementJour1 = getDebutdeJournee(dateDebut);
        Date finJour2 = getFindeJournee(dateFin);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesEntreDeuxJour = session.createCriteria(Pointage.class)
                .add(Restrictions.between("heure", commencementJour1, finJour2))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesEntreDeuxJour;
    }

    @Override
    public List<Pointage> PointagesDUnJourPourUnMatricule(String matricule, Date dateDuJour) {

        Date debutJournee = getDebutdeJournee(dateDuJour);
        Date finJournee = getFindeJournee(dateDuJour);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = session.createCriteria(Pointage.class)
                .add(Restrictions.eq("matricule", matricule))
                .add(Restrictions.between("heure", debutJournee, finJournee))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesJour;
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDatesPourUnMatricule(String matricule, Date dateDebut, Date dateFin) {

        Date commencementJour1 = getDebutdeJournee(dateDebut);
        Date finJour2 = getFindeJournee(dateFin);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = session.createCriteria(Pointage.class)
                .add(Restrictions.eq("matricule", matricule))
                .add(Restrictions.between("heure", commencementJour1, finJour2))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesJour;
    }

    @Override
    public List<Pointage> PointagesDUnJourPourUnIndividu(Individu individu, Date dateDuJour) {

        Date debutJournée = getDebutdeJournee(dateDuJour);
        Date finJournée = getFindeJournee(dateDuJour);

        String matriculeIndiv = individu.getMatricule();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = session.createCriteria(Pointage.class)
                .add(Restrictions.eq("matricule", matriculeIndiv))
                .add(Restrictions.between("heure", debutJournée, finJournée))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesJour;
    }

    @Override
    public List<Pointage> PointagesEntreDeuxDatesPourUnIndividu(Individu individu, Date dateDebut, Date dateFin) {

        Date commencementJour1 = getDebutdeJournee(dateDebut);
        Date finJour2 = getFindeJournee(dateFin);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        String matriculeIndiv = individu.getMatricule();

        List<Pointage> pointagesJours = session.createCriteria(Pointage.class)
                .add(Restrictions.eq("matricule", matriculeIndiv))
                .add(Restrictions.between("heure", commencementJour1, finJour2))
                .list();

        session.getTransaction().commit();
        session.close();

        return pointagesJours;
    }

    @Override
    public Pointage premierPointage(String matricule, Date jourLa) {
        Pointage p = null;

        List<Pointage> pointagesJourPourMatricule = PointagesDUnJourPourUnMatricule(matricule, jourLa);

        if (pointagesJourPourMatricule != null) {
            p = pointagesJourPourMatricule.get(0);
        }

        return p;
    }

    @Override
    public Pointage premiersPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin) {
        Pointage p = null;

        List<Pointage> pointagesEntreDeuxDatesMatricule = PointagesEntreDeuxDatesPourUnMatricule(matricule, dateDebut, dateFin);

        if (pointagesEntreDeuxDatesMatricule != null) {
            p = pointagesEntreDeuxDatesMatricule.get(0);
        }

        return p;
    }

    @Override
    public Pointage premierPointage(Individu individu, Date jourLa) {
        Pointage p = null;

        List<Pointage> pointagesJour = PointagesDUnJourPourUnIndividu(individu, jourLa);

        if (pointagesJour != null) {
            p = pointagesJour.get(0);
        }

        return p;
    }

    @Override
    public Pointage premiersPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin) {
        Pointage p = null;

        List<Pointage> pointagesEntreDeuxDates = PointagesEntreDeuxDatesPourUnIndividu(individu, dateDebut, dateFin);

        if (pointagesEntreDeuxDates != null) {
            p = pointagesEntreDeuxDates.get(0);
        }

        return p;
    }

    @Override
    public boolean estEnRetard(Individu individu, Date jourLa) {
        boolean retard = true;
        Pointage premierPointage = premierPointage(individu, jourLa);
        Date datePointage = premierPointage.getHeure();
        String heurePointage = ExtraireHeure(datePointage);

        if (ComparerHeure(HEURE_ARRIVEE, heurePointage) == true) {
            retard = false;
        }

        return retard;
    }

    @Override
    public List<Pointage> retardsPointagesDUnJourLa(Date jourLa) {
        List<Pointage> pointagesEnRetard = new ArrayList<>();

        List<Pointage> pointagesJour = PointagesDUnJour(jourLa);

        if (pointagesJour != null) {
            ListIterator<Pointage> it = pointagesJour.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                String heurePointage = ExtraireHeure(p.getHeure());

                if (ComparerHeure(HEURE_ARRIVEE, heurePointage) == false) {
                    pointagesEnRetard.add(p);
                }
            }

        }

        return pointagesEnRetard;
    }

    @Override
    public List<String> retardsMatriculeDUnJourLa(Date jourLa) {
        List<String> matriculeEnRetards = new ArrayList<>();

        List<Pointage> pointagesEnRetard = retardsPointagesDUnJourLa(jourLa);

        if (pointagesEnRetard != null) {
            ListIterator<Pointage> it = pointagesEnRetard.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                matriculeEnRetards.add(p.getMatricule());
            }
        }

        return matriculeEnRetards;
    }

    @Override
    public List<Individu> retardsIndividuDUnJourLa(Date jourLa) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individusEnRetardL = new ArrayList<>();

        List<String> matriculesIndividusEnRetard = retardsMatriculeDUnJourLa(jourLa);

        if (matriculesIndividusEnRetard != null) {
            ListIterator<String> it = matriculesIndividusEnRetard.listIterator();

            while (it.hasNext()) {
                String matricule = it.next();
                List<Individu> individu = session.createCriteria(Individu.class)
                        .add(Restrictions.eq("matricule", matricule))
                        .list();
                individusEnRetardL.add(individu.get(0));
            }
        }

        session.getTransaction().commit();
        session.close();

        return individusEnRetardL;
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(Date dateDebut, Date dateFin) {
        List<Pointage> enRetards = new ArrayList<>();
        List<Pointage> pointagesDeuxDates = PointagesEntreDeuxDates(dateDebut, dateFin);

        if (pointagesDeuxDates != null) {
            ListIterator<Pointage> it = pointagesDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage pointage = it.next();
                String heurePointage = ExtraireHeure(pointage.getHeure());

                if (ComparerHeure(HEURE_ARRIVEE, heurePointage) == false) {
                    enRetards.add(pointage);
                }
            }
        }
        return enRetards;
    }

    @Override
    public List<String> retardsMatriculeEntreDeuxDates(Date dateDebut, Date dateFin) {
        List<String> matriculesRetards = new ArrayList<>();
        List<Pointage> pointageRetardsDeuxDates = retardsPointagesEntreDeuxDates(dateDebut, dateFin);

        if (pointageRetardsDeuxDates != null) {
            ListIterator<Pointage> it = pointageRetardsDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                matriculesRetards.add(p.getMatricule());
            }
        }
        return matriculesRetards;
    }

    @Override
    public List<Individu> retardsIndividuEntreDeuxDates(Date dateDebut, Date dateFin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individusEnRetardDeuxDate = new ArrayList<>();

        List<String> matriculesIndividusEnRetardDeuxDates = retardsMatriculeEntreDeuxDates(dateDebut, dateFin);

        if (matriculesIndividusEnRetardDeuxDates != null) {
            ListIterator<String> it = matriculesIndividusEnRetardDeuxDates.listIterator();

            while (it.hasNext()) {
                String matricule = it.next();
                List<Individu> individu = session.createCriteria(Individu.class)
                        .add(Restrictions.eq("matricule", matricule))
                        .list();
                individusEnRetardDeuxDate.add(individu.get(0));
            }
        }

        session.getTransaction().commit();
        session.close();

        return individusEnRetardDeuxDate;
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin) {
        List<Pointage> retardsPointageByMatricule = new ArrayList<>();
        List<Pointage> retardsPointagesDeuxDates = retardsPointagesEntreDeuxDates(dateDebut, dateFin);

        if (retardsPointagesDeuxDates != null) {
            ListIterator<Pointage> it = retardsPointagesDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                if (p.getMatricule().equals(matricule)) {
                    retardsPointageByMatricule.add(p);
                }
            }
        }
        return retardsPointageByMatricule;
    }

    @Override
    public List<Pointage> retardsPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin) {

        List<Pointage> retardsPointageByMatriculeIndiv = new ArrayList<>();
        List<Pointage> retardsPointagesDeuxDates = retardsPointagesEntreDeuxDates(dateDebut, dateFin);

        if (retardsPointagesDeuxDates != null) {
            ListIterator<Pointage> it = retardsPointagesDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                if (p.getMatricule().equals(individu.getMatricule())) {
                    retardsPointageByMatriculeIndiv.add(p);
                }
            }
        }
        return retardsPointageByMatriculeIndiv;
    }

    @Override
    public List<Integer> getDiffHeureMinutes(String heure1, String heure2) {
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

    @Override
    public List<Long> cummulDesRetards(String matricule, Date dateDebut, Date dateFin) {

        List<Long> differenceTemps = new ArrayList<>();

        Long heures = 0L;
        Long minutes = 0L;

        List<Pointage> tousPointageEnRetardsMatriculeDeuxDates = retardsPointagesEntreDeuxDates(matricule, dateDebut, dateFin);

        if (tousPointageEnRetardsMatriculeDeuxDates != null) {
            ListIterator<Pointage> it = tousPointageEnRetardsMatriculeDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                heures += getDiffHeureMinutes(HEURE_ARRIVEE, ExtraireHeure(p.getHeure())).get(0);
                minutes += getDiffHeureMinutes(HEURE_ARRIVEE, ExtraireHeure(p.getHeure())).get(1);
            }
        }
        differenceTemps.add(heures);
        differenceTemps.add(minutes);

        return differenceTemps;
    }

    @Override
    public List<Long> cummulDesRetards(Individu individu, Date dateDebut, Date dateFin) {
        List<Long> differenceTempsIndiv = new ArrayList<>();

        Long heures = 0L;
        Long minutes = 0L;

        List<Pointage> tousPointageEnRetardsIndivDeuxDates = retardsPointagesEntreDeuxDates(individu, dateDebut, dateFin);

        if (tousPointageEnRetardsIndivDeuxDates != null) {
            ListIterator<Pointage> it = tousPointageEnRetardsIndivDeuxDates.listIterator();

            while (it.hasNext()) {
                Pointage p = it.next();
                heures += getDiffHeureMinutes(HEURE_ARRIVEE, ExtraireHeure(p.getHeure())).get(0);
                minutes += getDiffHeureMinutes(HEURE_ARRIVEE, ExtraireHeure(p.getHeure())).get(1);
            }
        }
        differenceTempsIndiv.add(heures);
        differenceTempsIndiv.add(minutes);

        return differenceTempsIndiv;
    }

    //***********************************************ODAY
    @Override
    public boolean estAbsent(Individu individu, Date jourLa) {
        Boolean abs = false;
        List<Pointage> pointageJournée = PointagesDUnJourPourUnIndividu(individu, jourLa);

        if (pointageJournée != null && pointageJournée.isEmpty()) {
            abs = true;
        }
        return abs;
    }

    @Override
    public List<String> matriculesAbsentsUnJourLa(Date jourLa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<String> matriculesAbsentsJour = new ArrayList<>();

        List<Pointage> pointagesJour = PointagesDUnJour(jourLa);
        List<Individu> individusList = session.createCriteria(Individu.class).list();

        if (pointagesJour != null && individusList != null) {

            ListIterator<Pointage> itPointage = pointagesJour.listIterator();
            while (itPointage.hasNext()) {

                ListIterator<Individu> itIndividu = individusList.listIterator();
                while (itIndividu.hasNext()) {

                    Pointage p = itPointage.next();
                    Individu i = itIndividu.next();

                    if (!i.getMatricule().equals(p.getMatricule())) {
                        matriculesAbsentsJour.add(i.getMatricule());
                    }

                }

            }
        }

        session.getTransaction().commit();
        session.close();

        return matriculesAbsentsJour;
    }

    @Override
    public List<Individu> individusAbsentsUnJourLa(Date jourLa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individusAbsentsJour = new ArrayList<>();

        List<Pointage> pointagesJour = PointagesDUnJour(jourLa);
        List<Individu> individusList = session.createCriteria(Individu.class).list();

        if (pointagesJour != null && individusList != null) {

            ListIterator<Pointage> itPointage = pointagesJour.listIterator();
            while (itPointage.hasNext()) {

                ListIterator<Individu> itIndividu = individusList.listIterator();
                while (itIndividu.hasNext()) {

                    Pointage p = itPointage.next();
                    Individu i = itIndividu.next();

                    if (!i.getMatricule().equals(p.getMatricule())) {
                        individusAbsentsJour.add(i);
                    }

                }

            }
        }

        session.getTransaction().commit();
        session.close();

        return individusAbsentsJour;
    }

    @Override
    public List<Date> joursAbsencesMatricule(String matricule) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Date> dateAbsenceJour = new ArrayList<>();
        
        List<Pointage> allPointages = session.createCriteria(Pointage.class).list();
        
        if (allPointages != null){
            ListIterator<Pointage> it = allPointages.listIterator();
            
            while (it.hasNext()) {
                Pointage p = it.next();
                
                if (!p.getMatricule().equals(matricule)){
                    dateAbsenceJour.add(p.getHeure());
                }
                
            }
        }

        session.getTransaction().commit();
        session.close();

        return dateAbsenceJour;
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
