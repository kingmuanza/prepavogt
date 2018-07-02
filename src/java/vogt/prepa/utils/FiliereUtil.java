package vogt.prepa.utils;

import java.util.Objects;
import vogt.prepa.entities.Classe;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.UtilisateurProfil;

public class FiliereUtil {
    
    public boolean isIn(Classe classe, UtilisateurProfil profil){
        if (profil.getUtilisateurProfilClasses().stream().anyMatch((upf) -> (Objects.equals(upf.getClasse().getIdclasse(), classe.getIdclasse())))) {
            return true;
        }
        
        return false;
    }
}
