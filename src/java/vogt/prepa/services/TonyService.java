package vogt.prepa.services;

import java.util.Date;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Employe;
import vogt.prepa.entities.Individu;
import vogt.prepa.utils.Statistique;

public interface TonyService {
    
    //Moyenne d'heure d'afrrivée des élèves à une date précise
    //Tu peux t'inspirer de EdgarService pour récupérer les premiers pointages par jour
    //Assures toi que l'individu soit un élève
    public String moyenneArriveeEleveDate(Date date);
    
    //Premier arrivé un jour la 
    public Individu premierArrivee(Date date);
    
    //Statistiques d'une salle de CLASSE
    //Une entite STATISTIQUE a été créée dans le package util
    //Tu peux t'inspirer de EdgarService pour récupérer les absences et les retards par jour
    public Statistique statistiques(Classe classe, Date date);
    public Statistique statistiquesDesEmployes(Date date);
    public Statistique statistiquesDesEnseignants(Date date);
    
    
    
    
}
