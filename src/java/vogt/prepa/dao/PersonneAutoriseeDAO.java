package vogt.prepa.dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.PersonneAutorisee;
import vogt.prepa.utils.HibernateUtil;

public class PersonneAutoriseeDAO {
    
    public boolean enregistrer(PersonneAutorisee personneAutorisee) {
        
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(personneAutorisee);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(PersonneAutorisee personneAutorisee) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(personneAutorisee);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public PersonneAutorisee get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        PersonneAutorisee personneAutorisee = (PersonneAutorisee) session.get(PersonneAutorisee.class, id);
        if (personneAutorisee == null) {
            throw new RuntimeException();
        } else {
            initialiser(personneAutorisee);
        }

        session.getTransaction().commit();
        session.close();

        return personneAutorisee;
    }
    
    public PersonneAutorisee getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        PersonneAutorisee personneAutorisee = (PersonneAutorisee) session.get(PersonneAutorisee.class, id);
        if (personneAutorisee == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return personneAutorisee;
    }
    
    public List<PersonneAutorisee> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<PersonneAutorisee> personneAutorisees = session.createCriteria(PersonneAutorisee.class).list();
        personneAutorisees.forEach((personneAutorisee) -> {
            initialiser(personneAutorisee);
        });

        session.getTransaction().commit();
        session.close();

        return personneAutorisees;

    }
    public List<PersonneAutorisee> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<PersonneAutorisee> personneAutorisees = session.createCriteria(PersonneAutorisee.class).list();
        personneAutorisees.forEach((personneAutorisee) -> {
//            initialiser(personneAutorisee);
        });

        session.getTransaction().commit();
        session.close();

        return personneAutorisees;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(PersonneAutorisee.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        PersonneAutorisee cours = get(id);
        return supprimer(cours);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public PersonneAutorisee get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public PersonneAutorisee getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(PersonneAutorisee personneAutorisee) {
     Hibernate.initialize(personneAutorisee.getIndividu());
     Hibernate.initialize(personneAutorisee.getAutorisation());
    }
}
