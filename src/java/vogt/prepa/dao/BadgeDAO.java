package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Badge;
import vogt.prepa.utils.HibernateUtil;

public class BadgeDAO {
    
    public boolean enregistrer(Badge badge) {
        
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(badge);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Badge badge) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(badge);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Badge get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Badge badge = (Badge) session.get(Badge.class, id);
        if (badge == null) {
            throw new RuntimeException();
        } else {
            initialiser(badge);
        }

        session.getTransaction().commit();
        session.close();

        return badge;
    }
    
    public Badge getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Badge badge = (Badge) session.get(Badge.class, id);
        if (badge == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return badge;
    }
    
    public List<Badge> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Badge> badges = session.createCriteria(Badge.class).list();
        badges.forEach((badge) -> {
            initialiser(badge);
        });

        session.getTransaction().commit();
        session.close();

        return badges;

    }
    public List<Badge> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Badge> badges = session.createCriteria(Badge.class).list();
        badges.forEach((badge) -> {
            initialiser(badge);
        });

        session.getTransaction().commit();
        session.close();

        return badges;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Badge.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(Badge badge) {
     Hibernate.initialize(badge.getEntrees());
    }
}
