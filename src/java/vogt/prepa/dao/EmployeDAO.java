package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Employe;
import vogt.prepa.utils.HibernateUtil;

public class EmployeDAO {
    
    public boolean enregistrer(Employe employe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(employe);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Employe employe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(employe);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Employe get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Employe employe = (Employe) session.get(Employe.class, id);
        if (employe == null) {
            throw new RuntimeException();
        } else {
            initialiser(employe);
        }

        session.getTransaction().commit();
        session.close();

        return employe;
    }
    
    public Employe getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Employe employe = (Employe) session.get(Employe.class, id);
        if (employe == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return employe;
    }
    
    public List<Employe> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Employe> employes = session.createCriteria(Employe.class).list();
        employes.forEach((employe) -> {
            initialiser(employe);
        });

        session.getTransaction().commit();
        session.close();

        return employes;

    }
    public List<Employe> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Employe> employes = session.createCriteria(Employe.class).list();
        employes.forEach((employe) -> {
            initialiser(employe);
        });

        session.getTransaction().commit();
        session.close();

        return employes;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Employe.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Employe employe) {
        
    }
}
