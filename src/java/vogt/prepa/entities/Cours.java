package vogt.prepa.entities;
// Generated 21 sept. 2018 07:21:11 by Hibernate Tools 4.3.1


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
 * Cours generated by hbm2java
 */
@Entity
@Table(name="cours"
    ,catalog="prepa"
)
public class Cours  implements java.io.Serializable {


     private Integer idcours;
     private Classe classe;
     private Matiere matiere;
     private Set<CoursEnseignant> coursEnseignants = new HashSet<CoursEnseignant>(0);

    public Cours() {
    }

    public Cours(Classe classe, Matiere matiere, Set<CoursEnseignant> coursEnseignants) {
       this.classe = classe;
       this.matiere = matiere;
       this.coursEnseignants = coursEnseignants;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcours", unique=true, nullable=false)
    public Integer getIdcours() {
        return this.idcours;
    }
    
    public void setIdcours(Integer idcours) {
        this.idcours = idcours;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idclasse")
    public Classe getClasse() {
        return this.classe;
    }
    
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idmatiere")
    public Matiere getMatiere() {
        return this.matiere;
    }
    
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="cours")
    public Set<CoursEnseignant> getCoursEnseignants() {
        return this.coursEnseignants;
    }
    
    public void setCoursEnseignants(Set<CoursEnseignant> coursEnseignants) {
        this.coursEnseignants = coursEnseignants;
    }




}


