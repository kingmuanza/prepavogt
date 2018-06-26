/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services;

import java.util.List;
import vogt.prepa.entities.Pointage;

/**
 *
 * @author muanz
 */
public interface DavidService {
    public static final String CHEMIN_DU_DOSSIER = "C:\\Users\\muanz\\OneDrive\\Documents\\SIA";
    
    //La fonction doit récupérer un texte du format si dessus et le transformer en liste de pointages
    //La première colonne représente le matricule
    public List<Pointage> textToPointages(String texte);
    
    
}
