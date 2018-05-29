package vogt.prepa.entities;
// Generated 23 mai 2018 16:30:47 by Hibernate Tools 4.3.1


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
 * Utilisateur generated by hbm2java
 */
@Entity
@Table(name="utilisateur"
    ,catalog="prepa"
)
public class Utilisateur  implements java.io.Serializable {


     private Integer idutilisateur;
     private Individu individu;
     private UtilisateurProfil utilisateurProfil;
     private String login;
     private String passe;

    public Utilisateur() {
    }

    public Utilisateur(Individu individu, UtilisateurProfil utilisateurProfil, String login, String passe) {
       this.individu = individu;
       this.utilisateurProfil = utilisateurProfil;
       this.login = login;
       this.passe = passe;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idutilisateur", unique=true, nullable=false)
    public Integer getIdutilisateur() {
        return this.idutilisateur;
    }
    
    public void setIdutilisateur(Integer idutilisateur) {
        this.idutilisateur = idutilisateur;
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
    @JoinColumn(name="idutilisateur_profil")
    public UtilisateurProfil getUtilisateurProfil() {
        return this.utilisateurProfil;
    }
    
    public void setUtilisateurProfil(UtilisateurProfil utilisateurProfil) {
        this.utilisateurProfil = utilisateurProfil;
    }

    
    @Column(name="login", length=45)
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    
    @Column(name="passe")
    public String getPasse() {
        return this.passe;
    }
    
    public void setPasse(String passe) {
        this.passe = passe;
    }




}


