/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import vogt.prepa.entities.Pointage;
import vogt.prepa.utils.HibernateUtil;

/**
 *
 * @author muanz
 */
public class MuanzaServiceImpl {

    public List<Pointage> premiersPointagesDuJourParMatricule(Date dateDuJour) {

        EdgarServiceImpl edgarService = new EdgarServiceImpl();

        Date debutJournee = edgarService.getDebutdeJournee(dateDuJour);
        Date finJournee = edgarService.getFindeJournee(dateDuJour);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = null;
        Criteria criteria = session.createCriteria(Pointage.class)
                .add(Restrictions.between("heure", debutJournee, finJournee));

        ProjectionList projection = Projections.projectionList();
        projection.add(Projections.sqlProjection("DATE_FORMAT(date, '%Y-%m-%d') as date", new String[]{"date"}, new Type[]{}));
        projection.add(Projections.distinct(Projections.property("matricule")), "matricule");
        //Add as many columns as you want using Projection
//        projection.add(Projections.property("heure"), "heure");
        criteria.setProjection(projection);
        criteria.setResultTransformer(Transformers.aliasToBean(Pointage.class));
        pointagesJour = criteria.list();

        for (Pointage p : pointagesJour) {
            System.out.print(p.getMatricule());
        }

        session.getTransaction().commit();
        session.close();

        return pointagesJour;
    }

    public List<Pointage> premiersPointagesDuJour() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointagesJour = null;
        Criteria criteria = session.createCriteria(Pointage.class);

        ProjectionList projection = Projections.projectionList();
        projection.add(Projections.sqlProjection("DATE_FORMAT(heure, '%Y-%m-%d') as heure", new String[]{"heure"}, new Type[]{StandardBasicTypes.STRING}));
        criteria.setProjection(projection);
        criteria.setResultTransformer(new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = null;
                try {
                    parsed = format.parse((String) tuple[0]);
                } catch (ParseException e) {
                }
                
                Pointage pointage = new Pointage();
                pointage.setHeure(parsed);
                //System.out.println(Arrays.toString(tuple));
                return pointage ;
            }

            @Override
            public List transformList(List list) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        pointagesJour = criteria.list();

        for (Pointage p : pointagesJour) {
            System.out.print(p.getMatricule());
        }

        session.getTransaction().commit();
        session.close();

        return pointagesJour;

    }

    public List<Pointage> pointagesDuJourDuMatricule(Pointage pointage, List<Pointage> pointages) {
        List<Pointage> pointagesDuJourDuMatricule = new ArrayList<Pointage>();
        for (Pointage p : pointages) {
            System.out.println("Pim pim");
            if (areSameDay(p.getHeure(), pointage.getHeure()) && p.getMatricule() == pointage.getMatricule()) {
                pointagesDuJourDuMatricule.add(p);
            }
        }
        return pointagesDuJourDuMatricule;
    }

    public boolean enRetard(Pointage pointage, List<Pointage> pointages) {
        List<Pointage> pointagesDuJourDuMatricule = pointagesDuJourDuMatricule(pointage, pointages);
        if (pointagesDuJourDuMatricule.size() > 0) {
            Pointage p = pointagesDuJourDuMatricule.get(0);
            if (p.getIdpointage() == pointage.getIdpointage()) {
                //C le premier pointage
                Calendar cal = Calendar.getInstance();
                cal.setTime(pointage.getHeure());
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                if (hour > 8) {
                    return true;
                }
            }
        }
        return false;
    }

    public void afficherListeDate(List<Date> dates) {
        System.out.println("****************** DATES *******************");
        for (Date date : dates) {
            System.out.println("Date : " + date.toLocaleString());
        }
    }

    public List<Date> listeDesDatesDePointages(List<Pointage> pointages) {
        List<Date> dates = new ArrayList<Date>();
        for (Pointage p : pointages) {
            if (!areInListDate(dates, p.getHeure())) {
                dates.add(p.getHeure());
            }
        }
        afficherListeDate(dates);
        return dates;
    }

    public boolean areSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }

    public boolean areInListDate(List<Date> dates, Date date1) {
        for (Date d : dates) {
            if (areSameDay(d, date1)) {
                return true;
            }
        }
        return false;
    }

}
