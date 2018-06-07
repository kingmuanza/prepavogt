package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Cours;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.UtilisateurProfilFiliere;
import vogt.prepa.utils.HibernateUtil;

public class FiliereDAO {

    public boolean enregistrer(Filiere filiere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(filiere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Filiere filiere) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(filiere);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Filiere get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Filiere filiere = (Filiere) session.get(Filiere.class, id);
        if (filiere == null) {
            throw new RuntimeException();
        } else {
            initialiser(filiere);
        }

        session.getTransaction().commit();
        session.close();

        return filiere;
    }

    public Filiere getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Filiere filiere = (Filiere) session.get(Filiere.class, id);
        if (filiere == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return filiere;
    }

    public List<Filiere> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Filiere> filieres = session.createCriteria(Filiere.class).list();
        filieres.forEach((filiere) -> {
            initialiser(filiere);
        });

        session.getTransaction().commit();
        session.close();

        return filieres;

    }

    public List<Filiere> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Filiere> filieres = session.createCriteria(Filiere.class).list();
        filieres.forEach((filiere) -> {
            initialiser(filiere);
        });

        session.getTransaction().commit();
        session.close();

        return filieres;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Filiere.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public void initialiser(Filiere filiere) {
        Hibernate.initialize(filiere.getEtudiants());
        Hibernate.initialize(filiere.getCourses());
        Hibernate.initialize(filiere.getUtilisateurProfilFilieres());
        for (Etudiant etu : filiere.getEtudiants()) {
            Hibernate.initialize(etu.getIndividu());
            Hibernate.initialize(etu.getAnneeScolaire());
            Hibernate.initialize(etu.getNiveauEtude());
        }
        for (Cours cou : filiere.getCourses()) {
            Hibernate.initialize(cou.getMatiere());
            Hibernate.initialize(cou.getFiliere());
            Hibernate.initialize(cou.getNiveauEtude());
            Hibernate.initialize(cou.getCoursEnseignants());
        }
        if (filiere.getUtilisateurProfilFilieres() != null) {
            for (UtilisateurProfilFiliere upf : filiere.getUtilisateurProfilFilieres()) {
                Hibernate.initialize(upf.getFiliere());
                Hibernate.initialize(upf.getUtilisateurProfil());
                if (upf.getUtilisateurProfil() != null) {
                    Hibernate.initialize(upf.getUtilisateurProfil());
                }
            }
        }
        for (UtilisateurProfilFiliere upf : filiere.getUtilisateurProfilFilieres()) {
            Hibernate.initialize(upf.getFiliere());
            Hibernate.initialize(upf.getUtilisateurProfil());
            Hibernate.initialize(upf.getUtilisateurProfil().getCode());
            if (upf.getUtilisateurProfil() != null) {
                Hibernate.initialize(upf.getUtilisateurProfil().getCode());
                Hibernate.initialize(upf.getUtilisateurProfil().getLibelle());
            }
        }
    }
}
