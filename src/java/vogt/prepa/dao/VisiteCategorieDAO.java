package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Entree;
import vogt.prepa.entities.Visite;
import vogt.prepa.entities.VisiteCategorie;
import vogt.prepa.utils.HibernateUtil;

public class VisiteCategorieDAO {

    public boolean enregistrer(VisiteCategorie visiteCategorie) {

        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(visiteCategorie);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(VisiteCategorie visiteCategorie) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(visiteCategorie);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public VisiteCategorie get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        VisiteCategorie visiteCategorie = (VisiteCategorie) session.get(VisiteCategorie.class, id);
        if (visiteCategorie == null) {
            throw new RuntimeException();
        } else {
            initialiser(visiteCategorie);
        }

        session.getTransaction().commit();
        session.close();

        return visiteCategorie;
    }

    public VisiteCategorie getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        VisiteCategorie visiteCategorie = (VisiteCategorie) session.get(VisiteCategorie.class, id);
        if (visiteCategorie == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return visiteCategorie;
    }

    public List<VisiteCategorie> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<VisiteCategorie> visiteCategories = session.createCriteria(VisiteCategorie.class).list();
        visiteCategories.forEach((visiteCategorie) -> {
            initialiser(visiteCategorie);
        });

        session.getTransaction().commit();
        session.close();

        return visiteCategories;

    }

    public List<VisiteCategorie> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<VisiteCategorie> visiteCategories = session.createCriteria(VisiteCategorie.class).list();
        visiteCategories.forEach((visiteCategorie) -> {
//            initialiser(visiteCategorie);
        });

        session.getTransaction().commit();
        session.close();

        return visiteCategories;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(VisiteCategorie.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public boolean supprimer(int id) {
        VisiteCategorie cours = get(id);
        return supprimer(cours);
    }

    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }

    public VisiteCategorie get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }

    public VisiteCategorie getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(VisiteCategorie visiteCategorie) {
        Hibernate.initialize(visiteCategorie.getVisites());
        for (Visite visite : visiteCategorie.getVisites()) {
            Hibernate.initialize(visite.getVisiteCategorie());
            Hibernate.initialize(visite.getIndividu());
            if (visite.getIndividu() != null) {
                Hibernate.initialize(visite.getIndividu().getEmployes());
                Hibernate.initialize(visite.getIndividu().getEtudiants());
                Hibernate.initialize(visite.getIndividu().getEnseignants());
            }
            Hibernate.initialize(visite.getEntrees());
            for (Entree e : visite.getEntrees()) {
                Hibernate.initialize(e.getBadge());
                Hibernate.initialize(e.getIndividu());
                Hibernate.initialize(e.getVisite());
            }
        }
    }
}
