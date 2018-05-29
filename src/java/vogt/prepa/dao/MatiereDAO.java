package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Matiere;
import vogt.prepa.utils.HibernateUtil;

public class MatiereDAO {
    
    public boolean enregistrer(Matiere matiere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(matiere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Matiere matiere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(matiere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Matiere get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Matiere matiere = (Matiere) session.get(Matiere.class, id);
        if (matiere == null) {
            throw new RuntimeException();
        } else {
            initialiser(matiere);
        }

        session.getTransaction().commit();
        session.close();

        return matiere;
    }
    
    public Matiere getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Matiere matiere = (Matiere) session.get(Matiere.class, id);
        if (matiere == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return matiere;
    }
    
    public List<Matiere> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Matiere> matieres = session.createCriteria(Matiere.class).list();
        matieres.forEach((matiere) -> {
            initialiser(matiere);
        });

        session.getTransaction().commit();
        session.close();

        return matieres;

    }
    public List<Matiere> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Matiere> matieres = session.createCriteria(Matiere.class).list();
        matieres.forEach((matiere) -> {
            initialiser(matiere);
        });

        session.getTransaction().commit();
        session.close();

        return matieres;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Matiere.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Matiere matiere) {
        
    }
}
