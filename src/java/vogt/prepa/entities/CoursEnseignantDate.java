package vogt.prepa.entities;
// Generated 2 juil. 2018 10:52:12 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CoursEnseignantDate generated by hbm2java
 */
@Entity
@Table(name="cours_enseignant_date"
    ,catalog="prepa"
)
public class CoursEnseignantDate  implements java.io.Serializable {


     private Integer idcoursEnseignantDate;
     private Integer idcoursEnseignant;
     private Integer jourSemaine;
     private Date heureDebut;
     private Date heureFin;

    public CoursEnseignantDate() {
    }

    public CoursEnseignantDate(Integer idcoursEnseignant, Integer jourSemaine, Date heureDebut, Date heureFin) {
       this.idcoursEnseignant = idcoursEnseignant;
       this.jourSemaine = jourSemaine;
       this.heureDebut = heureDebut;
       this.heureFin = heureFin;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idcours_enseignant_date", unique=true, nullable=false)
    public Integer getIdcoursEnseignantDate() {
        return this.idcoursEnseignantDate;
    }
    
    public void setIdcoursEnseignantDate(Integer idcoursEnseignantDate) {
        this.idcoursEnseignantDate = idcoursEnseignantDate;
    }

    
    @Column(name="idcours_enseignant")
    public Integer getIdcoursEnseignant() {
        return this.idcoursEnseignant;
    }
    
    public void setIdcoursEnseignant(Integer idcoursEnseignant) {
        this.idcoursEnseignant = idcoursEnseignant;
    }

    
    @Column(name="jour_semaine")
    public Integer getJourSemaine() {
        return this.jourSemaine;
    }
    
    public void setJourSemaine(Integer jourSemaine) {
        this.jourSemaine = jourSemaine;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="heure_debut", length=19)
    public Date getHeureDebut() {
        return this.heureDebut;
    }
    
    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="heure_fin", length=19)
    public Date getHeureFin() {
        return this.heureFin;
    }
    
    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }




}


