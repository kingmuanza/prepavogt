package vogt.prepa.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
    public List<Pointage> getall(String matricule) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointages = session.createCriteria(Pointage.class)
                .add(Restrictions.eq("machine", matricule))
                .list();
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
    
    public boolean supprimer(int id) {
        Pointage pointage = get(id);
        return supprimer(pointage);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Pointage get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Pointage getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Pointage pointage) {
        
    }
    
    public boolean verifierAvantEnregistrement(Pointage p){
        return true ;
    }
    
    /*
    methodes ajoutées N9-T
    */
    public List<Pointage> getByDate(Date date) {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Pointage> pointages = session.createCriteria(Pointage.class).add(Restrictions.between("heure", new Date("2018/07/02 00:00:00"),new Date("2018/07/02 23:59:59"))).list();
        pointages.forEach((pointage) -> {
            initialiser(pointage);
        });

        session.getTransaction().commit();
        session.close();

        return pointages;

    }
}
