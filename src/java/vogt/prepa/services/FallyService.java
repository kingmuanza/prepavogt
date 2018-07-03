/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services;

import java.util.Date;
import java.util.List;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Etudiant;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;

/**
 *
 * @author muanz
 */
public interface FallyService {
    
    //renvoi la liste des pointages d'une classe entre deux dates précises
    public List<Pointage> getByClasse(Classe classe, Date date1, Date date2);
    
    //retourne sous forme de String la moyenne des pointages d'une classe entre deux dates
    public String moyennePointageParClasse(Classe classe, Date date1, Date date2);
    
    //retourne sous forme de String les moyenne des pointages d'un individu entre deux dates
    public String monyennePointageParIndividu(Individu individu, Date date1, Date date2);
    
    //retourne sous forme de String les moyenne des pointages d'un individu entre deux dates
    public int totalRetardParIndividu(Individu individu, Date date1, Date date2);
    
    //renvoie les etudiants qui sont toujours à l'heure entre deux dates
    public List<Etudiant> toujoursPonctuel(Date date1, Date date2);
}
