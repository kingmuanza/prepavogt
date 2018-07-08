package vogt.prepa.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import vogt.prepa.entities.Individu;
import vogt.prepa.entities.Pointage;

/*
    Dans ce service on veut obtenir toutes les données qui serviront à produire des états statistiques
    Les états statistiques sur les POINTAGES BIOMETRIQUES
    L'entité POINTAGE sera la base de l'étude statistiques
    Afin de mieux comprendre ce qui sera fait, le fichier devra respecter un ensemble de règles très précises
 */

 /*
    REGLES TRES IMPORTANTES A SUIVRE SOUS PEINE D'AMENDE
    MODULARITE
        Une fonction ne doit comprende qu'un seul niveau d'indentation
        Si vous avez un if et un for créer une autre fonction pour le for

 */
public interface EdgarService {

    public static final String HEURE_ARRIVEE = "08:00:00";

    /*
        QUELQUES FONCTIONS QUE JE PENSE UTILES
     */
    //Obtenir l'heure d'une date
    public String ExtraireHeure(Date date);

    //Obtenir l'heure d'une date
    public String ExtraireDate(Date date);

    //Obtenir la date de début de la journée
    public Date getDebutdeJournee(Date journee);

    //Obtenir la date de fin de la journée
    public Date getFindeJournee(Date journee);

    //Comparer deux heures
    public boolean ComparerHeure(String heure1, String heure2);

    //Vérifier qu'un jour n'est pas un dimanche et n'est pas dans une période creuse
    public boolean JourDeClasse(Date dateDuJour);

    /*
        FONCTIONS POUR LE TRI DE DONNEES
     */
    //Obtenir la liste de tous les pointages d'une journée
    //Renvoie une liste vide si la dateDuJour est nulle
    public List<Pointage> PointagesDUnJour(Date dateDuJour);

    //Obtenir la liste de tous les pointages entre deux dates
    //Prévoir le traitement au cas où une des dates est nulle ou mm les deux
    public List<Pointage> PointagesEntreDeuxDates(Date dateDebut, Date dateFin);

    //Obtenir la liste de tous les pointages d'une journée pour un matricule donné
    //Renvoie une liste vide si la dateDuJour est nulle
    public List<Pointage> PointagesDUnJourPourUnMatricule(String matricule, Date dateDuJour);

    //Obtenir la liste de tous les pointages entre deux dates pour un matricule donné
    //Prévoir le traitement au cas où le matricule est nul
    public List<Pointage> PointagesEntreDeuxDatesPourUnMatricule(String matricule, Date dateDebut, Date dateFin);

    //Obtenir la liste de tous les pointages d'une journée pour un individu donné
    //Renvoie une liste vide si la dateDuJour est nulle
    public List<Pointage> PointagesDUnJourPourUnIndividu(Individu individu, Date dateDuJour);

    //Obtenir la liste de tous les pointages entre deux dates pour un individu donné
    //Prévoir le traitement au cas où l'individu est nul
    public List<Pointage> PointagesEntreDeuxDatesPourUnIndividu(Individu individu, Date dateDebut, Date dateFin);

    /*
        FONCTIONS POUR LES RETARDS
        On dit d'un MATRICULE qu'il est en retard si son premier pointage de la journée dépasse une heure donnée
        HEURE_ARRIVEE
     */
    //Récupérer le premier pointage d'un matricule d'un jour
    public Pointage premierPointage(String matricule, Date jourLa);

    //Récupérer les premiers pointages d'un matricule entre deux dates
    public Pointage premiersPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin);

    //Récupérer le premier pointage d'un individu d'un jour
    public Pointage premierPointage(Individu individu, Date jourLa);

    //Récupérer les premiers pointages d'un individu entre deux dates
    public Pointage premiersPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin);

    //Savoir si un individu a été en retard un jour là
    public boolean estEnRetard(Individu individu, Date jourLa);

    //Avoir la liste des pointages en retard un jour là
    public List<Pointage> retardsPointagesDUnJourLa(Date jourLa);

    //Avoir la liste des matricules en retard un jour là
    public List<String> retardsMatriculeDUnJourLa(Date jourLa);

    //Avoir la liste des individus en retard un jour là
    public List<Individu> retardsIndividuDUnJourLa(Date jourLa);

    //Avoir la liste des pointages en retard entre deux dates
    public List<Pointage> retardsPointagesEntreDeuxDates(Date dateDebut, Date dateFin);

    //Avoir la liste des matricules en retard entre deux dates
    public List<String> retardsMatriculeEntreDeuxDates(Date dateDebut, Date dateFin);

    //Avoir la liste des individus en retard entre deux dates
    public List<Individu> retardsIndividuEntreDeuxDates(Date dateDebut, Date dateFin);

    //Avoir la liste des pointages en retard entre deux dates pour un matricule donné
    public List<Pointage> retardsPointagesEntreDeuxDates(String matricule, Date dateDebut, Date dateFin);

    //Avoir la liste des pointages en retard entre deux dates pour un individu donné
    public List<Pointage> retardsPointagesEntreDeuxDates(Individu individu, Date dateDebut, Date dateFin);

    //Calcul la difference de temps entre deux heure en string et retourne le resultat sous forme d'un tableau
    //de deux entier dont le premier est la difference d'heure et le second la difference de minutes
    public List<Integer> getDiffHeureMinutes (String heure1, String heure2);

    //Avoir la somme des heures en retard entre deux dates pour un matricule donné
    public List<Long> cummulDesRetards(String matricule, Date dateDebut, Date dateFin);

    //Avoir la somme des heures en retard entre deux dates pour un individu donné
    public List<Long> cummulDesRetards(Individu individu, Date dateDebut, Date dateFin);

    /*
        FONCTIONS POUR LES ABSENCES
        On dit d'un MATRICULE qu'il est absent si il n'a pas pointé un jour de classe
     */
    //Savoir si un individu a été absent un jour là
    public boolean estAbsent(Individu individu, Date jourLa);

    //Avoir la liste des matricules absents un jour là
    public List<String> matriculesAbsentsUnJourLa(Date jourLa);

    //Avoir la liste des individus absents un jour là
    public List<Individu> individusAbsentsUnJourLa(Date jourLa);

    //Obtenir la liste des dates où un matricule a été absent
    public List<Date> joursAbsencesMatricule(String matricule);

    //Obtenir la liste des dates où un matricule a été absent
    public List<Date> joursAbsencesIndividu(Individu individu);

    //Nombre d'absence d'un matricule entre deux dates
    public int nombreAbsencesMatricule(String matricule, Date dateDebut, Date dateFin);

    //Nombre d'absence d'un individu entre deux dates
    public int nombreAbsencesIndividu(Individu individu, Date dateDebut, Date dateFin);

    //List d'absences des matricules entre deux dates
    public Map<String, List<Date>> AbsencesMatricules(Date dateDebut, Date dateFin);

    //List d'absence des individus entre deux dates
    public Map<Individu, List<Date>> AbsencesIndividus(Date dateDebut, Date dateFin);

    //Nombre d'absences des matricules entre deux dates
    public Map<String, Integer> nombreAbsencesMatricules(Date dateDebut, Date dateFin);

    //Nombre d'absence des individus entre deux dates
    public Map<Individu, Integer> nombreAbsencesIndividus(Date dateDebut, Date dateFin);

}
