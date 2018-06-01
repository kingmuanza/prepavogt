package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.HibernateUtil;

public class UtilisateurDAO {
    
    public Utilisateur getUserLoginInformations(String login, String passe){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        
        Utilisateur utilisateur = null;
        
        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class)
                .add(Restrictions.eq("login", login))
                    .add(Restrictions.eq("passe", passe))
                        .list();
        
        if (!utilisateurs.isEmpty()) {
            utilisateur = utilisateurs.get(0);  
            initialiser(utilisateur);
        }      
        
        session.getTransaction().commit();
        session.close();
        return utilisateur;
    }
    
    public boolean addUser(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(utilisateur);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean deleteUser(Utilisateur utilisateur) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(utilisateur);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Utilisateur get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
        if (utilisateur == null) {
            throw new RuntimeException();
        } else {
            initialiser(utilisateur);
        }

        session.getTransaction().commit();
        session.close();

        return utilisateur;
    }
    
    public Utilisateur getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Utilisateur utilisateur = (Utilisateur) session.get(Utilisateur.class, id);
        if (utilisateur == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return utilisateur;
    }
    
    public List<Utilisateur> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class).list();
        utilisateurs.forEach((utilisateur) -> {
            initialiser(utilisateur);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurs;

    }
    public List<Utilisateur> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Utilisateur> utilisateurs = session.createCriteria(Utilisateur.class).list();
        utilisateurs.forEach((utilisateur) -> {
            initialiser(utilisateur);
        });

        session.getTransaction().commit();
        session.close();

        return utilisateurs;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Utilisateur.class)
                                .setProjection(Projections.rowCount())
                                    .uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    //Recupérer tous les objets associés aux clés primaires 
    public void initialiser(Utilisateur utilisateur) {
        Hibernate.initialize(utilisateur.getIndividu());
    }
}
