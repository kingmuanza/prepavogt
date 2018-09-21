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
 * Poste generated by hbm2java
 */
@Entity
@Table(name="poste"
    ,catalog="prepa"
)
public class Poste  implements java.io.Serializable {


     private Integer idposte;
     private Poste poste;
     private String code;
     private String libelle;
     private Set<Employe> employes = new HashSet<Employe>(0);
     private Set<Poste> postes = new HashSet<Poste>(0);

    public Poste() {
    }

    public Poste(Poste poste, String code, String libelle, Set<Employe> employes, Set<Poste> postes) {
       this.poste = poste;
       this.code = code;
       this.libelle = libelle;
       this.employes = employes;
       this.postes = postes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idposte", unique=true, nullable=false)
    public Integer getIdposte() {
        return this.idposte;
    }
    
    public void setIdposte(Integer idposte) {
        this.idposte = idposte;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idposte_parent")
    public Poste getPoste() {
        return this.poste;
    }
    
    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    
    @Column(name="code", length=10)
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="poste")
    public Set<Employe> getEmployes() {
        return this.employes;
    }
    
    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="poste")
    public Set<Poste> getPostes() {
        return this.postes;
    }
    
    public void setPostes(Set<Poste> postes) {
        this.postes = postes;
    }




}


