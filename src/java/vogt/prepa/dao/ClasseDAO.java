package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Employe;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.utils.HibernateUtil;

public class ClasseDAO {

    public boolean enregistrer(Classe classe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(classe);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Classe classe) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(classe);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Classe get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Classe classe = (Classe) session.get(Classe.class, id);
        if (classe == null) {
            throw new RuntimeException();
        } else {
            initialiser(classe);
        }

        session.getTransaction().commit();
        session.close();

        return classe;
    }

    public Classe getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Classe classe = (Classe) session.get(Classe.class, id);
        if (classe == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return classe;
    }

    public List<Classe> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Classe> classes = session.createCriteria(Classe.class).list();
        classes.forEach((classe) -> {
            initialiser(classe);
        });

        session.getTransaction().commit();
        session.close();

        return classes;

    }

    public List<Classe> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Classe> classes = session.createCriteria(Classe.class).list();
        classes.forEach((classe) -> {
            initialiser(classe);
        });

        session.getTransaction().commit();
        session.close();

        return classes;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Classe.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }
    
    public boolean supprimer(int id) {
        Classe classe = get(id);
        return supprimer(classe);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Classe get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Classe getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Classe classe) {
        Hibernate.initialize(classe.getFiliere());
        Hibernate.initialize(classe.getNiveauEtude());
        Hibernate.initialize(classe.getEtudiants());
        for(Etudiant e : classe.getEtudiants()){
            Hibernate.initialize(e.getIndividu());
        }
    }
}
