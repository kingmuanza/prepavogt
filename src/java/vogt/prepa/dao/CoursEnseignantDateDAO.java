package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.CoursEnseignantDate;
import vogt.prepa.utils.HibernateUtil;

public class CoursEnseignantDateDAO {
    
    public boolean enregistrer(CoursEnseignantDate coursEnseignantDate) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(coursEnseignantDate);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(CoursEnseignantDate coursEnseignantDate) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(coursEnseignantDate);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public CoursEnseignantDate get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CoursEnseignantDate coursEnseignantDate = (CoursEnseignantDate) session.get(CoursEnseignantDate.class, id);
        if (coursEnseignantDate == null) {
            throw new RuntimeException();
        } else {
            initialiser(coursEnseignantDate);
        }

        session.getTransaction().commit();
        session.close();

        return coursEnseignantDate;
    }
    
    public CoursEnseignantDate getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CoursEnseignantDate coursEnseignantDate = (CoursEnseignantDate) session.get(CoursEnseignantDate.class, id);
        if (coursEnseignantDate == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return coursEnseignantDate;
    }
    
    public List<CoursEnseignantDate> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CoursEnseignantDate> coursEnseignantDates = session.createCriteria(CoursEnseignantDate.class).list();
        coursEnseignantDates.forEach((coursEnseignantDate) -> {
            initialiser(coursEnseignantDate);
        });

        session.getTransaction().commit();
        session.close();

        return coursEnseignantDates;

    }
    public List<CoursEnseignantDate> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CoursEnseignantDate> coursEnseignantDates = session.createCriteria(CoursEnseignantDate.class).list();
        coursEnseignantDates.forEach((coursEnseignantDate) -> {
//            initialiser(coursEnseignantDate);
        });

        session.getTransaction().commit();
        session.close();

        return coursEnseignantDates;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(CoursEnseignantDate.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        CoursEnseignantDate coursEnseignantDate = get(id);
        return supprimer(coursEnseignantDate);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public CoursEnseignantDate get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public CoursEnseignantDate getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(CoursEnseignantDate coursEnseignantDate) {
        
    }
}
