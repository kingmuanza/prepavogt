package vogt.prepa.entities;
// Generated 6 juin 2018 13:00:00 by Hibernate Tools 4.3.1


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
 * Visite generated by hbm2java
 */
@Entity
@Table(name="visite"
    ,catalog="prepa"
)
public class Visite  implements java.io.Serializable {


     private Integer idvisite;
     private Individu individu;
     private String nomComplet;
     private String motif;
     private String commentaire;
     private Set<Entree> entrees = new HashSet<Entree>(0);

    public Visite() {
    }

    public Visite(Individu individu, String nomComplet, String motif, String commentaire, Set<Entree> entrees) {
       this.individu = individu;
       this.nomComplet = nomComplet;
       this.motif = motif;
       this.commentaire = commentaire;
       this.entrees = entrees;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idvisite", unique=true, nullable=false)
    public Integer getIdvisite() {
        return this.idvisite;
    }
    
    public void setIdvisite(Integer idvisite) {
        this.idvisite = idvisite;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idindividu")
    public Individu getIndividu() {
        return this.individu;
    }
    
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

    
    @Column(name="nom_complet")
    public String getNomComplet() {
        return this.nomComplet;
    }
    
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    
    @Column(name="motif")
    public String getMotif() {
        return this.motif;
    }
    
    public void setMotif(String motif) {
        this.motif = motif;
    }

    
    @Column(name="commentaire")
    public String getCommentaire() {
        return this.commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="visite")
    public Set<Entree> getEntrees() {
        return this.entrees;
    }
    
    public void setEntrees(Set<Entree> entrees) {
        this.entrees = entrees;
    }




}


