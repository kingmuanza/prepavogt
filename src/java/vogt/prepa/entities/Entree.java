package vogt.prepa.entities;
// Generated 23 mai 2018 16:30:47 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entree generated by hbm2java
 */
@Entity
@Table(name="entree"
    ,catalog="prepa"
)
public class Entree  implements java.io.Serializable {


     private Integer identree;
     private Badge badge;
     private Individu individu;
     private Visite visite;
     private String nomComplet;
     private String motif;
     private String commentaire;
     private Date dateEntree;
     private Date dateSortie;

    public Entree() {
    }

    public Entree(Badge badge, Individu individu, Visite visite, String nomComplet, String motif, String commentaire, Date dateEntree, Date dateSortie) {
       this.badge = badge;
       this.individu = individu;
       this.visite = visite;
       this.nomComplet = nomComplet;
       this.motif = motif;
       this.commentaire = commentaire;
       this.dateEntree = dateEntree;
       this.dateSortie = dateSortie;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="identree", unique=true, nullable=false)
    public Integer getIdentree() {
        return this.identree;
    }
    
    public void setIdentree(Integer identree) {
        this.identree = identree;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idbadge")
    public Badge getBadge() {
        return this.badge;
    }
    
    public void setBadge(Badge badge) {
        this.badge = badge;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idindividu")
    public Individu getIndividu() {
        return this.individu;
    }
    
    public void setIndividu(Individu individu) {
        this.individu = individu;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idvisite")
    public Visite getVisite() {
        return this.visite;
    }
    
    public void setVisite(Visite visite) {
        this.visite = visite;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_entree", length=19)
    public Date getDateEntree() {
        return this.dateEntree;
    }
    
    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_sortie", length=19)
    public Date getDateSortie() {
        return this.dateSortie;
    }
    
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }




}


