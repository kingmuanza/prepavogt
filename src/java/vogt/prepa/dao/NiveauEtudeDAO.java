package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.NiveauEtude;
import vogt.prepa.utils.HibernateUtil;

public class NiveauEtudeDAO {
    
    public boolean enregistrer(NiveauEtude niveauEtude) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(niveauEtude);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(NiveauEtude niveauEtude) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(niveauEtude);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public NiveauEtude get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        NiveauEtude niveauEtude = (NiveauEtude) session.get(NiveauEtude.class, id);
        if (niveauEtude == null) {
            throw new RuntimeException();
        } else {
            initialiser(niveauEtude);
        }

        session.getTransaction().commit();
        session.close();

        return niveauEtude;
    }
    
    public NiveauEtude getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        NiveauEtude niveauEtude = (NiveauEtude) session.get(NiveauEtude.class, id);
        if (niveauEtude == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return niveauEtude;
    }
    
    public List<NiveauEtude> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<NiveauEtude> niveauEtudes = session.createCriteria(NiveauEtude.class).list();
        niveauEtudes.forEach((niveauEtude) -> {
            initialiser(niveauEtude);
        });

        session.getTransaction().commit();
        session.close();

        return niveauEtudes;

    }
    public List<NiveauEtude> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<NiveauEtude> niveauEtudes = session.createCriteria(NiveauEtude.class).list();
        niveauEtudes.forEach((niveauEtude) -> {
//            initialiser(niveauEtude);
        });

        session.getTransaction().commit();
        session.close();

        return niveauEtudes;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(NiveauEtude.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        NiveauEtude niveauEtude = get(id);
        return supprimer(niveauEtude);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public NiveauEtude get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public NiveauEtude getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(NiveauEtude niveauEtude) {
        
    }
}
