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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Badge generated by hbm2java
 */
@Entity
@Table(name="badge"
    ,catalog="prepa"
)
public class Badge  implements java.io.Serializable {


     private Integer idbadge;
     private String code;
     private String libelle;
     private Set<Entree> entrees = new HashSet<Entree>(0);

    public Badge() {
    }

    public Badge(String code, String libelle, Set<Entree> entrees) {
       this.code = code;
       this.libelle = libelle;
       this.entrees = entrees;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idbadge", unique=true, nullable=false)
    public Integer getIdbadge() {
        return this.idbadge;
    }
    
    public void setIdbadge(Integer idbadge) {
        this.idbadge = idbadge;
    }

    
    @Column(name="code", length=45)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="libelle")
    public String getLibelle() {
        return this.libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="badge")
    public Set<Entree> getEntrees() {
        return this.entrees;
    }
    
    public void setEntrees(Set<Entree> entrees) {
        this.entrees = entrees;
    }




}


