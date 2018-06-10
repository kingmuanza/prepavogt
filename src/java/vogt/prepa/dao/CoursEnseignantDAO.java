package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.CoursEnseignant;
import vogt.prepa.utils.HibernateUtil;

public class CoursEnseignantDAO {
    
    public boolean enregistrer(CoursEnseignant coursEnseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(coursEnseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(CoursEnseignant coursEnseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(coursEnseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public CoursEnseignant get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CoursEnseignant coursEnseignant = (CoursEnseignant) session.get(CoursEnseignant.class, id);
        if (coursEnseignant == null) {
            throw new RuntimeException();
        } else {
            initialiser(coursEnseignant);
        }

        session.getTransaction().commit();
        session.close();

        return coursEnseignant;
    }
    
    public CoursEnseignant getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        CoursEnseignant coursEnseignant = (CoursEnseignant) session.get(CoursEnseignant.class, id);
        if (coursEnseignant == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return coursEnseignant;
    }
    
    public List<CoursEnseignant> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CoursEnseignant> coursEnseignants = session.createCriteria(CoursEnseignant.class).list();
        coursEnseignants.forEach((coursEnseignant) -> {
            initialiser(coursEnseignant);
        });

        session.getTransaction().commit();
        session.close();

        return coursEnseignants;

    }
    public List<CoursEnseignant> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<CoursEnseignant> coursEnseignants = session.createCriteria(CoursEnseignant.class).list();
        coursEnseignants.forEach((coursEnseignant) -> {
            initialiser(coursEnseignant);
        });

        session.getTransaction().commit();
        session.close();

        return coursEnseignants;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(CoursEnseignant.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        CoursEnseignant coursEnseignant = get(id);
        return supprimer(coursEnseignant);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public CoursEnseignant get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public CoursEnseignant getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(CoursEnseignant coursEnseignant) {
        Hibernate.initialize(coursEnseignant.getCours());
        Hibernate.initialize(coursEnseignant.getEnseignant());
    }
}
