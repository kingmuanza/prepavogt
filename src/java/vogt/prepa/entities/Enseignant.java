package vogt.prepa.entities;
// Generated 3 juil. 2018 16:11:02 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Enseignant generated by hbm2java
 */
@Entity
@Table(name="enseignant"
    ,catalog="prepa"
)
public class Enseignant  implements java.io.Serializable {


     private Integer idenseignant;
     private Individu individu;
     private Set<CoursEnseignant> coursEnseignants = new HashSet<CoursEnseignant>(0);

    public Enseignant() {
    }

    public Enseignant(Individu individu, Set<CoursEnseignant> coursEnseignants) {
       this.individu = individu;
       this.coursEnseignants = coursEnseignants;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idenseignant", unique=true, nullable=false)
    public Integer getIdenseignant() {
        return this.idenseignant;
    }
    
    public void setIdenseignant(Integer idenseignant) {
        this.idenseignant = idenseignant;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idindividu")
    public Individu getIndividu() {
        return this.individu;
    }
    
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="enseignant")
    public Set<CoursEnseignant> getCoursEnseignants() {
        return this.coursEnseignants;
    }
    
    public void setCoursEnseignants(Set<CoursEnseignant> coursEnseignants) {
        this.coursEnseignants = coursEnseignants;
    }




}


