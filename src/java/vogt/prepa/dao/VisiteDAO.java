package vogt.prepa.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import vogt.prepa.entities.Entree;
import vogt.prepa.entities.Visite;
import vogt.prepa.utils.HibernateUtil;

public class VisiteDAO {
    
    public boolean enregistrer(Visite visite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(visite);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Visite visite) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(visite);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Visite get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Visite visite = (Visite) session.get(Visite.class, id);
        if (visite == null) {
            throw new RuntimeException();
        } else {
            initialiser(visite);
        }

        session.getTransaction().commit();
        session.close();

        return visite;
    }
    
    public Visite getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Visite visite = (Visite) session.get(Visite.class, id);
        if (visite == null) {
            throw new RuntimeException();
        } else {
            
        }

        session.getTransaction().commit();
        session.close();

        return visite;
    }
    
    public List<Visite> getall() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Visite> visites = session.createCriteria(Visite.class).list();
        visites.forEach((visite) -> {
            initialiser(visite);
        });

        session.getTransaction().commit();
        session.close();

        return visites;

    }
    
    public List<Visite> getallNonVenus() {
        
        List<Visite> visites = new ArrayList<Visite>();
        for(Visite v : getall()){
            if(v.getEntrees()!=null){
               if(v.getEntrees().isEmpty()){
                   visites.add(v);
               } 
            }else{
                visites.add(v);
            }
        }

        return visites;

    }
    public List<Visite> getAllLazy() {
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Visite> visites = session.createCriteria(Visite.class).list();
        visites.forEach((visite) -> {
//            initialiser(visite);
        });

        session.getTransaction().commit();
        session.close();

        return visites;

    }
    
    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Visite.class).setProjection(Projections.rowCount()).uniqueResult() ;
        
        session.getTransaction().commit();
        session.close();

        return n ;

    }
    
    public boolean supprimer(int id) {
        Visite visite = get(id);
        return supprimer(visite);
    }
    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }
    public Visite get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }
    public Visite getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Visite visite) {
        Hibernate.initialize(visite.getVisiteCategorie());
        Hibernate.initialize(visite.getIndividu());
        if(visite.getIndividu()!=null){
            Hibernate.initialize(visite.getIndividu().getEmployes());
            Hibernate.initialize(visite.getIndividu().getEtudiants());
            Hibernate.initialize(visite.getIndividu().getEnseignants());
        }
        Hibernate.initialize(visite.getEntrees());
        for (Entree e : visite.getEntrees()){
            Hibernate.initialize(e.getBadge());
            Hibernate.initialize(e.getIndividu());
            Hibernate.initialize(e.getVisite());
        }
        
    }
}
