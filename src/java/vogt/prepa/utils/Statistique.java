/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.utils;

import java.util.List;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;

/**
 *
 * @author muanz
 */
public class Statistique {
    
    private String moyenneHeure ;
    private List<Pointage> retards ; 
    private List<Individu> absents ; 
    private Individu premierArrivee ;

    /**
     * @return the moyenneHeure
     */
    public String getMoyenneHeure() {
        return moyenneHeure;
    }

    /**
     * @param moyenneHeure the moyenneHeure to set
     */
    public void setMoyenneHeure(String moyenneHeure) {
        this.moyenneHeure = moyenneHeure;
    }

    /**
     * @return the retards
     */
    public List<Pointage> getRetards() {
        return retards;
    }

    /**
     * @param retards the retards to set
     */
    public void setRetards(List<Pointage> retards) {
        this.retards = retards;
    }

    /**
     * @return the absents
     */
    public List<Individu> getAbsents() {
        return absents;
    }

    /**
     * @param absents the absents to set
     */
    public void setAbsents(List<Individu> absents) {
        this.absents = absents;
    }

    /**
     * @return the premierArrivee
     */
    public Individu getPremierArrivee() {
        return premierArrivee;
    }

    /**
     * @param premierArrivee the premierArrivee to set
     */
    public void setPremierArrivee(Individu premierArrivee) {
        this.premierArrivee = premierArrivee;
    }
    
    
    
    
}
