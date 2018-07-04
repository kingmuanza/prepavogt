/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import vogt.prepa.dao.EmployeDAO;
import vogt.prepa.dao.EnseignantDAO;
import vogt.prepa.dao.IndividuDAO;
import vogt.prepa.dao.PointageDAO;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Employe;
import vogt.prepa.entities.Enseignant;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.TonyService;
import vogt.prepa.utils.Statistique;

/**
 *
 * @author N9-T
 */
public class TonyServiceImpl implements TonyService {

    private IndividuDAO idao = new IndividuDAO();
    private EmployeDAO empdao = new EmployeDAO();
    private EnseignantDAO ensdao = new EnseignantDAO();
    private PointageDAO pdao = new PointageDAO();

    @Override
    public String moyenneArriveeEleveDate(Date date) {
        PointageDAO pdao = new PointageDAO();
        List<Pointage> lpointage = new EdgarServiceImpl().PointagesDUnJour(date);
        List<Date> ldate = new ArrayList<>();
        lpointage.forEach((p) -> {
            ldate.add(p.getHeure());
        });
        Date heure = dateMoyenne(ldate);
        return heure.getHours() + ":" + heure.getMinutes() + ":" + heure.getSeconds();
    }
    
    List<Pointage> retardsDuneClasse(Date date, Classe classe){
        List<Pointage> retardsClasse = new ArrayList<Pointage>();
        List<Pointage> retards = new EdgarServiceImpl().retardsPointagesDUnJourLa(date);
        for(Pointage p : retards){
            if(individuEstUnETudiantDelaClasse(p.getMachine(), classe)){
                retardsClasse.add(p);
            }
        }
        return retardsClasse;
    } 
    
    public boolean individuEstUnETudiantDelaClasse(String matricule, Classe classe){
        IndividuDAO individuDAO = new IndividuDAO(); 
        if(individuDAO.getByMatricule(matricule)!=null){
            Individu individu = individuDAO.getByMatricule(matricule);
            if(individu.getEtudiants()!=null && !individu.getEtudiants().isEmpty()){
                for(Etudiant e : individu.getEtudiants()){
                    if(e.getClasse()!=null){
                        if(e.getClasse().getIdclasse()==classe.getIdclasse()){
                            return true ;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Statistique statistiques(Classe classe, Date date) {
        Statistique stat = new Statistique(classe, date);

        List<Pointage> lpointage = new EdgarServiceImpl().PointagesDUnJour(date);

        if (findEtudiantByClasse(classe, lpointage).size() > 0) {
            stat.setPremierArrivee(findEtudiantByClasse(classe, lpointage).get(0).getIndividu());
        }

        stat.setRetards(retardsDuneClasse(date, classe));
        List<Individu> lesAbsents = new ArrayList<>();
        for (Etudiant etudiant : findEtudiantAbsents(classe, lpointage)) {
            lesAbsents.add(etudiant.getIndividu());
        }
        stat.setAbsents(lesAbsents);
        stat.setMoyenneHeure(moyenneArriveeEleveDate(date));
        return stat;
    }

    //il faut la date en paramètre ici
    @Override
    public Statistique statistiquesDesEmployes(Date date) {
        Statistique stat = new Statistique();
        List<Pointage> lpointage = pdao.getall();
        stat.setPremierArrivee(findEmployes(lpointage).get(0).getIndividu());
        stat.setRetards(new EdgarServiceImpl().retardsPointagesDUnJourLa(date));
        List<Individu> lesAbsents = new ArrayList<>();
        for (Employe employe : findEmployesAbsents(lpointage)) {
            lesAbsents.add(employe.getIndividu());
        }
        stat.setAbsents(lesAbsents);
        stat.setMoyenneHeure(moyenneArriveeEleveDate(null));
        return stat;
    }

    //il faut la date en paramètre ici
    @Override
    public Statistique statistiquesDesEnseignants(Date date) {
        Statistique stat = new Statistique();
        List<Pointage> lpointage = pdao.getall();
        stat.setPremierArrivee(findEnseignants(lpointage).get(0).getIndividu());
        stat.setRetards(new EdgarServiceImpl().retardsPointagesDUnJourLa(date));
        List<Individu> lesAbsents = new ArrayList<>();
        for (Enseignant enseignant : findEnseignantsAbsents(lpointage)) {
            lesAbsents.add(enseignant.getIndividu());
        }
        stat.setAbsents(lesAbsents);
        stat.setMoyenneHeure(moyenneArriveeEleveDate(null));
        return stat;
    }

    @Override
    public Individu premierArrivee(Date date) {
        List<Pointage> lpointage = pdao.getByDate(date);

        return lpointage.isEmpty() ? new Individu() : idao.getByMatricule(lpointage.get(0).getMatricule());
    }

    /*
    une méthode utilitaire privée pour reecupérer tous les étudiants d'un salle de classe à partir de la liste
    des pointages
     */
    public List<Etudiant> findEtudiantByClasse(Classe classe, List<Pointage> lpointage) {
        List<Etudiant> lesEtudiants = new ArrayList<>();
        for (Pointage p : lpointage) {
            Individu individu = idao.getByMatricule(p.getMachine());
            if (individu != null) {
                if (!individu.getEtudiants().isEmpty()) {
                    Etudiant e = individu.getEtudiants().iterator().next();
                    if (e.getClasse().getIdclasse()==classe.getIdclasse()) {
                        lesEtudiants.add(e);
                    }
                }
            }
        }
        return lesEtudiants;
    }

    /*
    une méthode utilitaire privée pour reecupérer tous les employés à partir de la liste
    des pointages
     */
    private List<Employe> findEmployes(List<Pointage> lpointage) {
        List<Employe> lesEmployes = new ArrayList<>();
        String matricule;
        for (Pointage p : lpointage) {
            if (!idao.getByMatricule(p.getMatricule()).getEtudiants().isEmpty()) {
                Employe e = idao.getByMatricule(p.getMatricule()).getEmployes().iterator().next();
                lesEmployes.add(e);
            }
        }
        return lesEmployes;
    }

    /*
    une méthode utilitaire privée pour reecupérer tous les enseignants à partir de la liste
    des pointages
     */
    private List<Enseignant> findEnseignants(List<Pointage> lpointage) {
        List<Enseignant> lesEnseignants = new ArrayList<>();
        String matricule;
        for (Pointage p : lpointage) {
            if (!idao.getByMatricule(p.getMatricule()).getEtudiants().isEmpty()) {
                Enseignant e = idao.getByMatricule(p.getMatricule()).getEnseignants().iterator().next();
                lesEnseignants.add(e);
            }
        }
        return lesEnseignants;
    }

    /*
    methode pour recupérer tous les etudiants absents par classe
     */
    private List<Etudiant> findEtudiantAbsents(Classe classe, List<Pointage> lpointage) {
        List<Etudiant> lesEtudiants = new ArrayList<>();
        List<Etudiant> etudiantsPresents = findEtudiantByClasse(classe, lpointage);
        for (Etudiant etudiant : classe.getEtudiants()) {
            if (!estPresent(etudiant, etudiantsPresents)) {
                lesEtudiants.add(etudiant);
            }
        }
        return lesEtudiants;
    }
    
    public boolean estPresent(Etudiant etudiant, List<Etudiant> etudiants){
        for(Etudiant e : etudiants){
            if(e.getIdetudiant()==etudiant.getIdetudiant()){
                return true ;
            }
        }
        return false ;
    }

    /*
    methode pour recupérer tous les employes absents
     */
    private List<Employe> findEmployesAbsents(List<Pointage> lpointage) {
        List<Employe> lesEmployes = new ArrayList<>();
        String matricule;
        List<Employe> employesPresents = findEmployes(lpointage);
        for (Employe employe : empdao.getall()) {
            if (!employesPresents.contains(employe.getIndividu())) {
                lesEmployes.add(employe);
            }
        }
        return lesEmployes;
    }

    /*
    methode pour recupérer tous les enseignants absents
     */
    private List<Enseignant> findEnseignantsAbsents(List<Pointage> lpointage) {
        List<Enseignant> lesEnseignants = new ArrayList<>();
        String matricule;
        List<Enseignant> enseignantsPresents = findEnseignants(lpointage);
        for (Enseignant enseignant : ensdao.getall()) {
            if (!enseignantsPresents.contains(enseignant.getIndividu())) {
                lesEnseignants.add(enseignant);
            }
        }
        return lesEnseignants;
    }
    
    public static  Date dateMoyenne(List<Date> ldate) {
       Double somHeur = 0.0, somMin = 0.0, somSec = 0.0;
       for (Date d : ldate) {
           somSec += d.getSeconds();
           somMin += d.getMinutes();
           somHeur += d.getHours();
       }
       Date date = new Date();

       Double heur = somHeur / ldate.size();
       System.out.println("HEURE ******" + heur+ " *****INT VALUE"+heur.intValue());
       Double min = (somMin  / (ldate.size())) + ((heur - heur.intValue()) * 60);
       if(min>=60){
           min -= 60;
           heur ++;
       }
       System.out.println("Min ******" + min);
       Double sec = (somSec / (ldate.size() )) + ((min - min.intValue()) * 60);
       if(sec>=60){
           sec -= 60;
           min++;
       }
       System.out.println("SEC ******" + sec);
       date.setHours(Integer.parseInt(String.valueOf(Math.round(heur))));
       date.setMinutes(Integer.parseInt(String.valueOf(Math.round(min))));
       date.setSeconds(Integer.parseInt(String.valueOf(Math.round(sec))));
       return date;
   }
}
