package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Pointage;
import vogt.prepa.utils.HibernateUtil;

public class PointageDAO {
    
    public boolean enregistrer(Pointage pointage) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(pointage);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Pointage pointage) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(pointage);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Pointage get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Pointage pointage = (Pointage) session.get(Pointage.class, id);
        if (pointage == null) {
            throw new RuntimeException();
        } else {
            initialiser(pointage);
        }

        session.getTransaction().commit();
        session.close();

        return pointage;
    }
    
    public Pointage getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Pointage pointage = (Pointage) session.get(Pointage.class, id);
        if (pointage == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return pointage;
    }
    
    public List<Pointage> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointages = session.createCriteria(Pointage.class).list();
        pointages.forEach((pointage) -> {
            initialiser(pointage);
        });

        session.getTransaction().commit();
        session.close();

        return pointages;

    }
    public List<Pointage> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointages = session.createCriteria(Pointage.class).list();
        pointages.forEach((pointage) -> {
            initialiser(pointage);
        });

        session.getTransaction().commit();
        session.close();

        return pointages;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Pointage.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Pointage pointage) {
        
    }
}
