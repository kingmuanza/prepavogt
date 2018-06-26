/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vogt.prepa.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;
import java.util.List;
import java.text.*;
import vogt.prepa.entities.Pointage;
import vogt.prepa.services.DavidService;

/**
 *
 * @author zos hall
 */
public class DavidServiceImpl implements DavidService {

//    String url = " ";
//    String nomFichierPointage = " ";
//    File pointage = new File(url, nomFichierPointage);
    @Override
    public List<Pointage> textToPointages(String chemin) {
        List<Pointage> pList = new ArrayList<Pointage>();
        try {
            Scanner scanner = new Scanner(chemin);
            Pointage point = new Pointage();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            while (true) {
                //Utilisation de la methode scanner pour recuperer les lignes de pointages
                
                try {
                    point.setMatricule(scanner.next());
                    String name = scanner.next();
                    String heur = scanner.next();
                    try {
                        point.setHeure(sdf.parse(heur)) ;

                    } catch (Exception except) {
                        System.out.println("Parse Exception");
                    }
                    String verify = scanner.next();
                    point.setMode(scanner.next());
                    point.setMachine(scanner.next());
                    String exception = scanner.next();
                    String checktype = scanner.next();
                    point.setNumero(scanner.next());
                    point.setIomd(scanner.next());
                    String sDate = scanner.next();
                    //Ajout du pointage recuperer dans la liste des pointages
                    pList.add(point);
                    System.out.println(point.getNumero() + "|" + point.getMachine() + "|" + point.getMatricule() + "|" + point.getMode() + "|" + point.getIomd() + "|" + point.getHeure());
                } catch (Exception exception) {
                    break;
                }
            }

            scanner.close();
        } catch (Exception fnfe) {
            System.out.println("Le fichier n'a pas été trouvé");
        }
        return pList;
    }
}
