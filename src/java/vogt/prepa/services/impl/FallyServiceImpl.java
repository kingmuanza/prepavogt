///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package vogt.prepa.services.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import vogt.prepa.dao.IndividuDAO;
//import vogt.prepa.entities.Classe;
//import vogt.prepa.entities.Etudiant;
//import vogt.prepa.entities.Individu;
//import vogt.prepa.entities.Pointage;
//import vogt.prepa.services.EdgarService;
//import vogt.prepa.services.FallyService;
//
///**
// *
// * @author N9-T
// */
//public class FallyServiceImpl implements FallyService {
//
//    @Override
//    public List<Pointage> getByClasse(Classe classe, Date date1, Date date2) {
//        EdgarService esrv = new EdgarServiceImpl();
//        List<Pointage> pointagesEntreDeuxDates = esrv.PointagesEntreDeuxDates(date1, date2);
//        List<Pointage> pointagesDeLaClasse = new ArrayList<>();
//        IndividuDAO idao = new IndividuDAO();
//        for (Pointage p : pointagesEntreDeuxDates) {
//            if (!idao.getByMatricule(p.getMatricule()).getEtudiants().isEmpty()) {
//                Etudiant e = idao.getByMatricule(p.getMatricule()).getEtudiants().iterator().next();
//                if (e.getClasse().equals(classe)) {
//                    pointagesDeLaClasse.add(p);
//                }
//
//            }
//        }
//        return pointagesDeLaClasse;
//    }
//
//    @Override
//    public String moyennePointageParClasse(Classe classe, Date date1, Date date2) {
//        List<Date> ldate = new ArrayList<>();
//        getByClasse(classe, date1, date2).forEach((p) -> {
//            ldate.add(p.getHeure());
//        });
//        return TonyServiceImpl.dateMoyenne(ldate).toString();
//    }
//
//    @Override
//    public String monyennePointageParIndividu(Individu individu, Date date1, Date date2) {
//        List<Date> ldate = new ArrayList<>();
//        EdgarService esrv = new EdgarServiceImpl();
//        esrv.PointagesEntreDeuxDatesPourUnIndividu(individu, date2, date2).forEach((p) -> {
//            ldate.add(p.getHeure());
//        });
//        return TonyServiceImpl.dateMoyenne(ldate).toString();
//    }
//
//    @Override
//    public int totalRetardParIndividu(Individu individu, Date date1, Date date2) {
//        EdgarService esrv = new EdgarServiceImpl();
//        int count = 0;
//        for (Individu i : esrv.retardsIndividuEntreDeuxDates(date1, date2)) {
//            if (i.equals(individu)) {
//                count++;
//            }
//        }
//        return count;
//    }
//
//    @Override
//    public List<Etudiant> toujoursPonctuel(Date date1, Date date2) {
//        List<Etudiant> lesEtudiants = new ArrayList<>();
//        EdgarService esrv = new EdgarServiceImpl();
//        List<Pointage> lesPointages = esrv.PointagesEntreDeuxDates(date1, date2);
//        List<Pointage> lesRetards = esrv.PointagesEntreDeuxDates(date1, date2);
//        lesPointages.removeAll(lesRetards);
//        IndividuDAO idao = new IndividuDAO();
//        for (Pointage p : lesPointages) {
//            Individu i = idao.getByMatricule(p.getMatricule());
//            if (!i.getEtudiants().isEmpty()) {
//                Etudiant e = i.getEtudiants().iterator().next();
//                lesEtudiants.add(e);
//            }
//        }
//
//        return lesEtudiants;
//    }
//
//}
