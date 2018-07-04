package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Employe;
import vogt.prepa.entities.Poste;
import vogt.prepa.utils.HibernateUtil;

public class PosteDAO {

    public boolean enregistrer(Poste poste) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(poste);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Poste poste) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(poste);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Poste get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Poste poste = (Poste) session.get(Poste.class, id);
        if (poste == null) {
            throw new RuntimeException();
        } else {
            initialiser(poste);
        }

        session.getTransaction().commit();
        session.close();

        return poste;
    }

    public Poste getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Poste poste = (Poste) session.get(Poste.class, id);
        if (poste == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return poste;
    }

    public List<Poste> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Poste> postes = session.createCriteria(Poste.class).list();
        postes.forEach((poste) -> {
            initialiser(poste);
        });

        session.getTransaction().commit();
        session.close();

        return postes;

    }

    public List<Poste> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Poste> postes = session.createCriteria(Poste.class).list();
        postes.forEach((poste) -> {
//            initialiser(poste);
        });

        session.getTransaction().commit();
        session.close();

        return postes;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Poste.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }
    
    public boolean supprimer(int id) {
        Poste poste = get(id);
        return supprimer(poste);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Poste get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Poste getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Poste poste) {
        Hibernate.initialize(poste.getEmployes());
        for (Employe employe : poste.getEmployes()) {
            Hibernate.initialize(employe.getIndividu());
        }
    }
}
