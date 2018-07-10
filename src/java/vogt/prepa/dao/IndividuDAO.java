package vogt.prepa.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Utilisateur;
import vogt.prepa.utils.HibernateUtil;

public class IndividuDAO {

    public boolean enregistrer(Individu individu) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.saveOrUpdate(individu);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public boolean supprimer(Individu individu) {
        boolean isGood = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            session.delete(individu);
            isGood = true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
        return isGood;
    }

    public Individu get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Individu individu = (Individu) session.get(Individu.class, id);
        if (individu == null) {
            throw new RuntimeException();
        } else {
            initialiser(individu);
        }

        session.getTransaction().commit();
        session.close();

        return individu;
    }

    public Individu getLazy(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Individu individu = (Individu) session.get(Individu.class, id);
        if (individu == null) {
            throw new RuntimeException();
        } else {

        }

        session.getTransaction().commit();
        session.close();

        return individu;
    }

    public Individu getByMatricule(String matricule) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individus = session.createCriteria(Individu.class)
                .add(Restrictions.eq("matricule", matricule))
                .list();
        individus.forEach((individu) -> {
            initialiser(individu);
        });

        session.getTransaction().commit();
        session.close();
        if (individus.size() > 0) {
            return individus.get(0);
        } else {
            return null;
        }

    }

    public List<Individu> getall() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individus = session.createCriteria(Individu.class).list();
        individus.forEach((individu) -> {
            initialiser(individu);
        });

        session.getTransaction().commit();
        session.close();

        return individus;

    }

    public List<Individu> getallUnsused() {

        List<Individu> individus = new ArrayList<>();
        for (Individu individu : getall()) {
            if (!(individu.getEtudiants() != null && !individu.getEtudiants().isEmpty())) {
                if (!(individu.getEmployes()!= null && !individu.getEmployes().isEmpty())) {
                    if (!(individu.getEnseignants()!= null && !individu.getEnseignants().isEmpty())) {
                        individus.add(individu);
                    }
                }
            }
        }
        return individus;

    }

    public List<Individu> getAllLazy() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        List<Individu> individus = session.createCriteria(Individu.class).list();
        individus.forEach((individu) -> {
//            initialiser(individu);
        });

        session.getTransaction().commit();
        session.close();

        return individus;

    }

    public Number getNumber() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        Number n = (Number) session.createCriteria(Individu.class).setProjection(Projections.rowCount()).uniqueResult();

        session.getTransaction().commit();
        session.close();

        return n;

    }

    public boolean supprimer(int id) {
        Individu individu = get(id);
        return supprimer(individu);
    }

    public boolean supprimer(String id) {
        int i = Integer.parseInt(id);
        return supprimer(i);
    }

    public Individu get(String id) {
        int i = Integer.parseInt(id);
        return get(i);
    }

    public Individu getLazy(String id) {
        int i = Integer.parseInt(id);
        return getLazy(i);
    }

    public void initialiser(Individu individu) {
        Hibernate.initialize(individu.getEmployes());
        Hibernate.initialize(individu.getEnseignants());
        Hibernate.initialize(individu.getEtudiants());
    }

    /*
    methodes ajoutées N9-T
     */
    public List<Individu> getByUtilisateur(Utilisateur utilisateur) {
        List<Individu> lesIndividus = new ArrayList<>();
        if (utilisateur.getUtilisateurProfil().getVoirEmploye()) {
            for (Individu i : this.getall()) {
                if (!i.getEmployes().isEmpty()) {
                    lesIndividus.add(i);
                }
            }
        }
        if (utilisateur.getUtilisateurProfil().getVoirEnseignant()) {
            for (Individu i : this.getall()) {
                if (!i.getEnseignants().isEmpty()) {
                    lesIndividus.add(i);
                }
            }
        }
        return lesIndividus;
    }
    
    public Individu selectionnerIndividu(String matricule, List<Individu> individus){
        for(Individu i : individus){
            
            if(matricule.equals(i.getMatricule())){
                return i ;
            }
        }
        return null ;
    }
}
