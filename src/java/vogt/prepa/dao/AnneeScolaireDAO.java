package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.AnneeScolaire;
import vogt.prepa.utils.HibernateUtil;

public class AnneeScolaireDAO {
    
    public boolean enregistrer(AnneeScolaire anneeScolaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(anneeScolaire);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(AnneeScolaire anneeScolaire) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(anneeScolaire);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public AnneeScolaire get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        AnneeScolaire anneeScolaire = (AnneeScolaire) session.get(AnneeScolaire.class, id);
        if (anneeScolaire == null) {
            throw new RuntimeException();
        } else {
            initialiser(anneeScolaire);
        }

        session.getTransaction().commit();
        session.close();

        return anneeScolaire;
    }
    
    public AnneeScolaire getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        AnneeScolaire anneeScolaire = (AnneeScolaire) session.get(AnneeScolaire.class, id);
        if (anneeScolaire == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return anneeScolaire;
    }
    
    public List<AnneeScolaire> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<AnneeScolaire> anneeScolaires = session.createCriteria(AnneeScolaire.class).list();
        anneeScolaires.forEach((anneeScolaire) -> {
            initialiser(anneeScolaire);
        });

        session.getTransaction().commit();
        session.close();

        return anneeScolaires;

    }
    public List<AnneeScolaire> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<AnneeScolaire> anneeScolaires = session.createCriteria(AnneeScolaire.class).list();
        anneeScolaires.forEach((anneeScolaire) -> {
            initialiser(anneeScolaire);
        });

        session.getTransaction().commit();
        session.close();

        return anneeScolaires;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(AnneeScolaire.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    //hjklkjhghjk
    public void initialiser(AnneeScolaire anneeScolaire) {
        Hibernate.initialize(anneeScolaire.getEtudiants());
    }
}
