package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.utils.HibernateUtil;

public class EnseignantDAO {
    
    public boolean enregistrer(Enseignant enseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(enseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Enseignant enseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(enseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Enseignant get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Enseignant enseignant = (Enseignant) session.get(Enseignant.class, id);
        if (enseignant == null) {
            throw new RuntimeException();
        } else {
            initialiser(enseignant);
        }

        session.getTransaction().commit();
        session.close();

        return enseignant;
    }
    
    public Enseignant getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Enseignant enseignant = (Enseignant) session.get(Enseignant.class, id);
        if (enseignant == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return enseignant;
    }
    
    public List<Enseignant> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Enseignant> enseignants = session.createCriteria(Enseignant.class).list();
        enseignants.forEach((enseignant) -> {
            initialiser(enseignant);
        });

        session.getTransaction().commit();
        session.close();

        return enseignants;

    }
    public List<Enseignant> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Enseignant> enseignants = session.createCriteria(Enseignant.class).list();
        enseignants.forEach((enseignant) -> {
            initialiser(enseignant);
        });

        session.getTransaction().commit();
        session.close();

        return enseignants;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Enseignant.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Enseignant enseignant) {
        
    }
}
