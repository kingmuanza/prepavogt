package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.UtilisateurProfil;
import vogt.prepa.utils.HibernateUtil;

public class UtilisateurProfilDAO {
    
    public boolean enregistrer(UtilisateurProfil utilisateurProfil) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(utilisateurProfil);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(UtilisateurProfil utilisateurProfil) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateurProfil);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public UtilisateurProfil get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfil utilisateurProfil = (UtilisateurProfil) session.get(UtilisateurProfil.class, id);
        if (utilisateurProfil == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateurProfil);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfil;
    }
    
    public UtilisateurProfil getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfil utilisateurProfil = (UtilisateurProfil) session.get(UtilisateurProfil.class, id);
        if (utilisateurProfil == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfil;
    }
    
    public List<UtilisateurProfil> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfil> utilisateurProfils = session.createCriteria(UtilisateurProfil.class).list();
        utilisateurProfils.forEach((utilisateurProfil) -> {
            initialiser(utilisateurProfil);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfils;

    }
    public List<UtilisateurProfil> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfil> utilisateurProfils = session.createCriteria(UtilisateurProfil.class).list();
        utilisateurProfils.forEach((utilisateurProfil) -> {
            initialiser(utilisateurProfil);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfils;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(UtilisateurProfil.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        UtilisateurProfil utilisateurProfil = get(id);
        return supprimer(utilisateurProfil);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public UtilisateurProfil get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public UtilisateurProfil getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(UtilisateurProfil utilisateurProfil) {
        
    }
}
