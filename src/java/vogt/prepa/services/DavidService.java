/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services;

import java.util.List;
import vogt.prepa.entities.Pointage;

/*
    Ce service est dédié à la communication entre la POINTEUSE BIOMETRIQUE et la base de données de l'application
    Suivant le protocole définit par YOMI MBIANDA ALAIN ROGER Grand maitre réseauticien
       *La pointeuse va stocker toutes les 30 min les données de pointages dans un fichier qui se situera 
        toujours dans le même dossier et dont le nom comportera la date exacte de l'export des données
    Le but de ce service est donc de récupérer les données de pointage dans le dernier fichier d'export
    Et des les placer dans la base de données
    Il faudra pour des raisons de sécurité vérifier que la donnée a entrer n'existe pas en base 
 */

 /*

Ac-No                    Name                     sTime              Verify Mode Machine              Exception  checktype  sensorid   workcode sDate      
2                        YOMI                     19/06/2018 15:11   Password &  2                               O          1                 0 19/06/2018 
3                        MBIANDA                  19/06/2018 15:11   Password &  2                               O          1                 0 19/06/2018 
4                        ROGER                    19/06/2018 15:12   Password &  2                               O          1                 0 19/06/2018 
5                        ALAIN                    19/06/2018 15:12   Password &  2                               O          1                 0 19/06/2018 
6                        Muanza                   19/06/2018 15:12   Password &  2                               O          1                 0 19/06/2018 
7                        Thony                   
 */
public interface DavidService {
    public static final String CHEMIN_DU_DOSSIER = "C:\\Users\\muanz\\OneDrive\\Documents\\SIA";
    
    //La fonction doit récupérer un texte du format si dessus et le transformer en liste de pointages
    public List<Pointage> textToPointages(String texte);
    
    
}
