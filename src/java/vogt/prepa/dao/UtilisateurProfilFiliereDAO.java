package vogt.prepa.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.UtilisateurProfilFiliere;
import vogt.prepa.utils.HibernateUtil;

public class UtilisateurProfilFiliereDAO {
    
    public boolean enregistrer(UtilisateurProfilFiliere utilisateurProfilFiliere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(utilisateurProfilFiliere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(UtilisateurProfilFiliere utilisateurProfilFiliere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateurProfilFiliere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public UtilisateurProfilFiliere get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfilFiliere utilisateurProfilFiliere = (UtilisateurProfilFiliere) session.get(UtilisateurProfilFiliere.class, id);
        if (utilisateurProfilFiliere == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateurProfilFiliere);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFiliere;
    }
    
    public UtilisateurProfilFiliere getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfilFiliere utilisateurProfilFiliere = (UtilisateurProfilFiliere) session.get(UtilisateurProfilFiliere.class, id);
        if (utilisateurProfilFiliere == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFiliere;
    }
    
    public List<UtilisateurProfilFiliere> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfilFiliere> utilisateurProfilFilieres = session.createCriteria(UtilisateurProfilFiliere.class).list();
        utilisateurProfilFilieres.forEach((utilisateurProfilFiliere) -> {
            initialiser(utilisateurProfilFiliere);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFilieres;

    }
    public List<UtilisateurProfilFiliere> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfilFiliere> utilisateurProfilFilieres = session.createCriteria(UtilisateurProfilFiliere.class).list();
        utilisateurProfilFilieres.forEach((utilisateurProfilFiliere) -> {
            initialiser(utilisateurProfilFiliere);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFilieres;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(UtilisateurProfilFiliere.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }

    public void initialiser(UtilisateurProfilFiliere utilisateurProfilFiliere) {
        
    }
}
