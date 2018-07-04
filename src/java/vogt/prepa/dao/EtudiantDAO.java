package vogt.prepa.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.utils.HibernateUtil;

public class EtudiantDAO {

    public boolean enregistrer(Etudiant etudiant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(etudiant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Etudiant etudiant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(etudiant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Etudiant get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Etudiant etudiant = (Etudiant) session.get(Etudiant.class, id);
        if (etudiant == null) {
            throw new RuntimeException();
        } else {
            initialiser(etudiant);
        }

        session.getTransaction().commit();
        session.close();

        return etudiant;
    }

    public Etudiant getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Etudiant etudiant = (Etudiant) session.get(Etudiant.class, id);
        if (etudiant == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return etudiant;
    }

    public List<Etudiant> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Etudiant> etudiants = session.createCriteria(Etudiant.class).list();
        etudiants.forEach((etudiant) -> {
            initialiser(etudiant);
        });

        session.getTransaction().commit();
        session.close();

        return etudiants;

    }
    

    public List<Etudiant> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Etudiant> etudiants = session.createCriteria(Etudiant.class).list();
        etudiants.forEach((etudiant) -> {
//            initialiser(etudiant);
        });

        session.getTransaction().commit();
        session.close();

        return etudiants;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Etudiant.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }
    
    public boolean supprimer(int id) {
        Etudiant etudiant = get(id);
        return supprimer(etudiant);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Etudiant get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Etudiant getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Etudiant etudiant) {
        Hibernate.initialize(etudiant.getIndividu());
        Hibernate.initialize(etudiant.getAnneeScolaire());
        Hibernate.initialize(etudiant.getClasse());
        if(etudiant.getClasse()!=null){
            Hibernate.initialize(etudiant.getClasse().getFiliere());
            Hibernate.initialize(etudiant.getClasse().getNiveauEtude());
        }
    }
}
