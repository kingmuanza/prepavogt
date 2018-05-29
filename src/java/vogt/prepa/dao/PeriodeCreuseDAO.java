package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.PeriodeCreuse;
import vogt.prepa.utils.HibernateUtil;

public class PeriodeCreuseDAO {
    
    public boolean enregistrer(PeriodeCreuse periodeCreuse) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(periodeCreuse);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(PeriodeCreuse periodeCreuse) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(periodeCreuse);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public PeriodeCreuse get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        PeriodeCreuse periodeCreuse = (PeriodeCreuse) session.get(PeriodeCreuse.class, id);
        if (periodeCreuse == null) {
            throw new RuntimeException();
        } else {
            initialiser(periodeCreuse);
        }

        session.getTransaction().commit();
        session.close();

        return periodeCreuse;
    }
    
    public PeriodeCreuse getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        PeriodeCreuse periodeCreuse = (PeriodeCreuse) session.get(PeriodeCreuse.class, id);
        if (periodeCreuse == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return periodeCreuse;
    }
    
    public List<PeriodeCreuse> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<PeriodeCreuse> periodeCreuses = session.createCriteria(PeriodeCreuse.class).list();
        periodeCreuses.forEach((periodeCreuse) -> {
            initialiser(periodeCreuse);
        });

        session.getTransaction().commit();
        session.close();

        return periodeCreuses;

    }
    public List<PeriodeCreuse> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<PeriodeCreuse> periodeCreuses = session.createCriteria(PeriodeCreuse.class).list();
        periodeCreuses.forEach((periodeCreuse) -> {
            initialiser(periodeCreuse);
        });

        session.getTransaction().commit();
        session.close();

        return periodeCreuses;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(PeriodeCreuse.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(PeriodeCreuse periodeCreuse) {
        
    }
}
