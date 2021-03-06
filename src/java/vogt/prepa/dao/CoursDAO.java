package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Cours;
import vogt.prepa.entities.CoursEnseignant;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.utils.HibernateUtil;

public class CoursDAO {

    public boolean enregistrer(Cours cours) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(cours);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Cours cours) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(cours);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Cours get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Cours cours = (Cours) session.get(Cours.class, id);
        if (cours == null) {
            throw new RuntimeException();
        } else {
            initialiser(cours);
        }

        session.getTransaction().commit();
        session.close();

        return cours;
    }

    public Cours getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Cours cours = (Cours) session.get(Cours.class, id);
        if (cours == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return cours;
    }

    public List<Cours> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Cours> courss = session.createCriteria(Cours.class).list();
        courss.forEach((cours) -> {
            initialiser(cours);
        });

        session.getTransaction().commit();
        session.close();

        return courss;

    }

    public List<Cours> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Cours> courss = session.createCriteria(Cours.class).list();
        courss.forEach((cours) -> {
            initialiser(cours);
        });

        session.getTransaction().commit();
        session.close();

        return courss;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Cours.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }
    
    public boolean supprimer(int id) {
        Cours cours = get(id);
        return supprimer(cours);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Cours get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Cours getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Cours cours) {
        Hibernate.initialize(cours.getClasse());
        if(cours.getClasse()!=null){
            Hibernate.initialize(cours.getClasse().getFiliere());
            Hibernate.initialize(cours.getClasse().getNiveauEtude());
        }
        Hibernate.initialize(cours.getMatiere());
        Hibernate.initialize(cours.getCoursEnseignants());
        for (CoursEnseignant ce : cours.getCoursEnseignants()) {
            Hibernate.initialize(ce.getCours());
            Hibernate.initialize(ce.getEnseignant());
            if(ce.getEnseignant()!=null){
                Hibernate.initialize(ce.getEnseignant().getIndividu());
            }
            
            
        }
    }
}
