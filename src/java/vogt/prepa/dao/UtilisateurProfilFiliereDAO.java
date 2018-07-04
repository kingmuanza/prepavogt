package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.UtilisateurProfil;
import vogt.prepa.entities.UtilisateurProfilClasse;
import vogt.prepa.utils.HibernateUtil;

public class UtilisateurProfilFiliereDAO {
    
    public boolean enregistrer(UtilisateurProfilClasse utilisateurProfilClasse) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(utilisateurProfilClasse);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(UtilisateurProfilClasse utilisateurProfilFiliere) {
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

    public UtilisateurProfilClasse get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfilClasse utilisateurProfilFiliere = (UtilisateurProfilClasse) session.get(UtilisateurProfilClasse.class, id);
        if (utilisateurProfilFiliere == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateurProfilFiliere);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFiliere;
    }
    
    public UtilisateurProfilClasse getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        UtilisateurProfilClasse utilisateurProfilFiliere = (UtilisateurProfilClasse) session.get(UtilisateurProfilClasse.class, id);
        if (utilisateurProfilFiliere == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFiliere;
    }
    
    public List<UtilisateurProfilClasse> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfilClasse> utilisateurProfilFilieres = session.createCriteria(UtilisateurProfilClasse.class).list();
        utilisateurProfilFilieres.forEach((utilisateurProfilFiliere) -> {
            initialiser(utilisateurProfilFiliere);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFilieres;

    }
    public List<UtilisateurProfilClasse> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<UtilisateurProfilClasse> utilisateurProfilFilieres = session.createCriteria(UtilisateurProfilClasse.class).list();
        utilisateurProfilFilieres.forEach((utilisateurProfilFiliere) -> {
//            initialiser(utilisateurProfilFiliere);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurProfilFilieres;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(UtilisateurProfilClasse.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        UtilisateurProfilClasse utilisateurProfilFiliere = get(id);
        return supprimer(utilisateurProfilFiliere);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public UtilisateurProfilClasse get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public UtilisateurProfilClasse getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(UtilisateurProfilClasse utilisateurProfilClasse) {
        Hibernate.initialize(utilisateurProfilClasse.getUtilisateurProfil());
    }
}
