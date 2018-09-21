package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Autorisation;
import vogt.prepa.utils.HibernateUtil;

public class AutorisationDAO {
    
    public boolean enregistrer(Autorisation autorisation) {
        
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(autorisation);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Autorisation autorisation) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(autorisation);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Autorisation get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Autorisation autorisation = (Autorisation) session.get(Autorisation.class, id);
        if (autorisation == null) {
            throw new RuntimeException();
        } else {
            initialiser(autorisation);
        }

        session.getTransaction().commit();
        session.close();

        return autorisation;
    }
    
    public Autorisation getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Autorisation autorisation = (Autorisation) session.get(Autorisation.class, id);
        if (autorisation == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return autorisation;
    }
    
    public List<Autorisation> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Autorisation> autorisations = session.createCriteria(Autorisation.class).list();
        autorisations.forEach((autorisation) -> {
            initialiser(autorisation);
        });

        session.getTransaction().commit();
        session.close();

        return autorisations;

    }
    public List<Autorisation> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Autorisation> autorisations = session.createCriteria(Autorisation.class).list();
        autorisations.forEach((autorisation) -> {
//            initialiser(autorisation);
        });

        session.getTransaction().commit();
        session.close();

        return autorisations;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Autorisation.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        Autorisation cours = get(id);
        return supprimer(cours);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Autorisation get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Autorisation getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Autorisation autorisation) {
     Hibernate.initialize(autorisation.getPersonneAutorisees());
    }
}
