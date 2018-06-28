package vogt.prepa.utils;

import java.util.Objects;
import vogt.prepa.entities.Cours;
import vogt.prepa.entities.CoursEnseignant;
import vogt.prepa.entities.Enseignant;

public class CoursUtil {

    public boolean isIn(Cours cours, Enseignant enseignant) {
        if (enseignant != null && cours != null) {
            if (enseignant.getCoursEnseignants() != null) {
                for(CoursEnseignant ce : enseignant.getCoursEnseignants()){
                    if(ce.getCours()!=null && ce.getCours().getIdcours()==cours.getIdcours()){
                        return true;
                    }
                }

            }
        }

        return false;
    }
}
