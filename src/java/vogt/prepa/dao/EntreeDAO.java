package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Entree;
import vogt.prepa.entities.Visite;
import vogt.prepa.utils.HibernateUtil;

public class EntreeDAO {
    
    public boolean enregistrer(Entree entree) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(entree);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Entree entree) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(entree);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Entree get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Entree entree = (Entree) session.get(Entree.class, id);
        if (entree == null) {
            throw new RuntimeException();
        } else {
            initialiser(entree);
        }

        session.getTransaction().commit();
        session.close();

        return entree;
    }
    
    public Entree getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Entree entree = (Entree) session.get(Entree.class, id);
        if (entree == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return entree;
    }
    
    public List<Entree> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Entree> entrees = session.createCriteria(Entree.class).list();
        entrees.forEach((entree) -> {
            initialiser(entree);
        });

        session.getTransaction().commit();
        session.close();

        return entrees;

    }
    public List<Entree> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Entree> entrees = session.createCriteria(Entree.class).list();
        entrees.forEach((entree) -> {
//            initialiser(entree);
        });

        session.getTransaction().commit();
        session.close();

        return entrees;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Entree.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        Entree entree = get(id);
        return supprimer(entree);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Entree get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Entree getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Entree entree) {
        Hibernate.initialize(entree.getIndividu());
        if(entree.getIndividu()!=null){
            Hibernate.initialize(entree.getIndividu().getEnseignants());
            Hibernate.initialize(entree.getIndividu().getEtudiants());
            Hibernate.initialize(entree.getIndividu().getEmployes());
        }
        Hibernate.initialize(entree.getVisite());
        Hibernate.initialize(entree.getBadge());
        
    }
}
