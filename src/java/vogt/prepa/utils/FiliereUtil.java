package vogt.prepa.utils;

import java.util.Objects;
import vogt.prepa.entities.Filiere;
import vogt.prepa.entities.UtilisateurProfil;

public class FiliereUtil {
    
    public boolean isIn(Filiere filiere, UtilisateurProfil profil){
        if (profil.getUtilisateurProfilFilieres().stream().anyMatch((upf) -> (Objects.equals(upf.getFiliere().getIdfiliere(), filiere.getIdfiliere())))) {
            return true;
        }
        
        return false;
    }
}
