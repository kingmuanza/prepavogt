package vogt.prepa.services;

/*
    Ce service est dédié à la communication entre la POINTEUSE BIOMETRIQUE et la base de données de l'application
    Suivant le protocole définit par YOMI MBIANDA ALAIN ROGER Grand maitre réseauticien
       *La pointeuse va stocker toutes les 30 min les données de pointages dans un fichier qui se situera 
        toujours dans le même dossier et dont le nom comportera la date exacte de l'export des données
    Le but de ce service est donc de récupérer les données de pointage dans le dernier fichier d'export
    Et des les placer dans la base de données
    Il faudra pour des raisons de sécurité vérifier que la donnée a entrer n'existe pas en base 
 */
public interface JoelService {
    
}
