package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Visite;
import vogt.prepa.utils.HibernateUtil;

public class VisiteDAO {
    
    public boolean enregistrer(Visite visite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(visite);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Visite visite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(visite);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Visite get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Visite visite = (Visite) session.get(Visite.class, id);
        if (visite == null) {
            throw new RuntimeException();
        } else {
            initialiser(visite);
        }

        session.getTransaction().commit();
        session.close();

        return visite;
    }
    
    public Visite getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Visite visite = (Visite) session.get(Visite.class, id);
        if (visite == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return visite;
    }
    
    public List<Visite> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Visite> visites = session.createCriteria(Visite.class).list();
        visites.forEach((visite) -> {
            initialiser(visite);
        });

        session.getTransaction().commit();
        session.close();

        return visites;

    }
    public List<Visite> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Visite> visites = session.createCriteria(Visite.class).list();
        visites.forEach((visite) -> {
            initialiser(visite);
        });

        session.getTransaction().commit();
        session.close();

        return visites;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Visite.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Visite visite) {
        
    }
}
