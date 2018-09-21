package vogt.prepa.entities;
// Generated 21 sept. 2018 07:21:11 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Etudiant generated by hbm2java
 */
@Entity
@Table(name="etudiant"
    ,catalog="prepa"
)
public class Etudiant  implements java.io.Serializable {


     private Integer idetudiant;
     private AnneeScolaire anneeScolaire;
     private Classe classe;
     private Individu individu;

    public Etudiant() {
    }

    public Etudiant(AnneeScolaire anneeScolaire, Classe classe, Individu individu) {
       this.anneeScolaire = anneeScolaire;
       this.classe = classe;
       this.individu = individu;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idetudiant", unique=true, nullable=false)
    public Integer getIdetudiant() {
        return this.idetudiant;
    }
    
    public void setIdetudiant(Integer idetudiant) {
        this.idetudiant = idetudiant;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idannee_scolaire")
    public AnneeScolaire getAnneeScolaire() {
        return this.anneeScolaire;
    }
    
    public void setAnneeScolaire(AnneeScolaire anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
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
    @JoinColumn(name="idindividu")
    public Individu getIndividu() {
        return this.individu;
    }
    
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }




}


