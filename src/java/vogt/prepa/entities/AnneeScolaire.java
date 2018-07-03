package vogt.prepa.entities;
// Generated 3 juil. 2018 16:11:02 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AnneeScolaire generated by hbm2java
 */
@Entity
@Table(name="annee_scolaire"
    ,catalog="prepa"
)
public class AnneeScolaire  implements java.io.Serializable {


     private Integer idanneeScolaire;
     private String code;
     private String libelle;
     private Date dateDebut;
     private Date dateFin;
     private Set<Etudiant> etudiants = new HashSet<Etudiant>(0);

    public AnneeScolaire() {
    }

    public AnneeScolaire(String code, String libelle, Date dateDebut, Date dateFin, Set<Etudiant> etudiants) {
       this.code = code;
       this.libelle = libelle;
       this.dateDebut = dateDebut;
       this.dateFin = dateFin;
       this.etudiants = etudiants;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idannee_scolaire", unique=true, nullable=false)
    public Integer getIdanneeScolaire() {
        return this.idanneeScolaire;
    }
    
    public void setIdanneeScolaire(Integer idanneeScolaire) {
        this.idanneeScolaire = idanneeScolaire;
    }

    
    @Column(name="code", length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="libelle", length=45)
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_debut", length=10)
    public Date getDateDebut() {
        return this.dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="date_fin", length=10)
    public Date getDateFin() {
        return this.dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="anneeScolaire")
    public Set<Etudiant> getEtudiants() {
        return this.etudiants;
    }
    
    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }




}


