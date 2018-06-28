package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.CoursEnseignant;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.utils.HibernateUtil;

public class EnseignantDAO {

    public boolean enregistrer(Enseignant enseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(enseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Enseignant enseignant) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(enseignant);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Enseignant get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Enseignant enseignant = (Enseignant) session.get(Enseignant.class, id);
        if (enseignant == null) {
            throw new RuntimeException();
        } else {
            initialiser(enseignant);
        }

        session.getTransaction().commit();
        session.close();

        return enseignant;
    }

    public Enseignant getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Enseignant enseignant = (Enseignant) session.get(Enseignant.class, id);
        if (enseignant == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return enseignant;
    }

    public List<Enseignant> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Enseignant> enseignants = session.createCriteria(Enseignant.class).list();
        enseignants.forEach((enseignant) -> {
            initialiser(enseignant);
        });

        session.getTransaction().commit();
        session.close();

        return enseignants;

    }

    public List<Enseignant> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Enseignant> enseignants = session.createCriteria(Enseignant.class).list();
        enseignants.forEach((enseignant) -> {
            initialiser(enseignant);
        });

        session.getTransaction().commit();
        session.close();

        return enseignants;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Enseignant.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public boolean supprimer(int id) {
        Enseignant enseignant = get(id);
        return supprimer(enseignant);
    }

    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }

    public Enseignant get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }

    public Enseignant getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Enseignant enseignant) {
        Hibernate.initialize(enseignant.getIndividu());
        Hibernate.initialize(enseignant.getCoursEnseignants());
        for (CoursEnseignant ce : enseignant.getCoursEnseignants()) {
            Hibernate.initialize(ce.getCours());
            if (ce.getCours() != null) {
                Hibernate.initialize(ce.getCours().getMatiere());
                Hibernate.initialize(ce.getCours().getClasse());
                if (ce.getCours().getClasse() != null) {
                    Hibernate.initialize(ce.getCours().getClasse().getFiliere());
                    Hibernate.initialize(ce.getCours().getClasse().getNiveauEtude());
                }
            }

        }
    }
}
