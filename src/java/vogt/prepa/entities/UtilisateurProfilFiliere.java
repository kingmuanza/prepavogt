package vogt.prepa.entities;
// Generated 6 juin 2018 13:00:00 by Hibernate Tools 4.3.1


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
 * UtilisateurProfilFiliere generated by hbm2java
 */
@Entity
@Table(name="utilisateur_profil_filiere"
    ,catalog="prepa"
)
public class UtilisateurProfilFiliere  implements java.io.Serializable {


     private Integer idutilisateurProfilFiliere;
     private Filiere filiere;
     private UtilisateurProfil utilisateurProfil;

    public UtilisateurProfilFiliere() {
    }

    public UtilisateurProfilFiliere(Filiere filiere, UtilisateurProfil utilisateurProfil) {
       this.filiere = filiere;
       this.utilisateurProfil = utilisateurProfil;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idutilisateur_profil_filiere", unique=true, nullable=false)
    public Integer getIdutilisateurProfilFiliere() {
        return this.idutilisateurProfilFiliere;
    }
    
    public void setIdutilisateurProfilFiliere(Integer idutilisateurProfilFiliere) {
        this.idutilisateurProfilFiliere = idutilisateurProfilFiliere;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idfiliere")
    public Filiere getFiliere() {
        return this.filiere;
    }
    
    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idutilisateur_profil")
    public UtilisateurProfil getUtilisateurProfil() {
        return this.utilisateurProfil;
    }
    
    public void setUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfil = utilisateurProfil;
    }




}


