///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package vogt.prepa.services.impl;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//import vogt.prepa.entities.Pointage;
//import vogt.prepa.services.DavidService;
//
///**
// *
// * @author zos hall
// */
//public class DavidServiceImpl  implements DavidService {
//
////    String url = " ";
////    String nomFichierPointage = " ";
////    File pointage = new File(url, nomFichierPointage);
//    @Override
//    public List<Pointage> pointagesRecupererFichier(File pointage) {
//
//        try {
//            Scanner scanner = new Scanner(pointage);
//            String numero;
//            String machine;
//            String matricule;
//            String mode;
//            String iomd;
//            Date heure;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
//            while (true) {
//                //Utilisation de la methode scanner pour recuperer les lignes de pointages
//                try {
//                    numero = scanner.next();
//                    machine = scanner.next();
//                    matricule = scanner.next();
//                    mode = scanner.next();
//                    iomd = scanner.next();
//                    String heur = scanner.next();
//                    sdf = new SimpleDateFormat("yyyy MMM d");
//                    Date heure = null;
//                    try {
//                        heure = sdf.parse(heur);
//
//                        
//                    } catch (ParseException e) {
//                        System.out.println("Parse Exception");
//                    }
//                    Pointage p = new Pointage(numero, machine, matricule, mode, iomd, heure);
//                    List<Pointage> pList = new ArrayList<Pointage>();
//                    pList.add(p);
//                    System.out.println(numero + "|" + machine + "|" + matricule + "|" + mode + "|" + iomd + "|" + heure);
//                } catch (Exception exception) {
//                    break;
//                }
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("Le fichier n'a pas été trouvé");
//        }
//        return pList;
//    }
//}
//
