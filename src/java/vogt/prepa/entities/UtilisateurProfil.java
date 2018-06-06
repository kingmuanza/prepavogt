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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * UtilisateurProfil generated by hbm2java
 */
@Entity
@Table(name="utilisateur_profil"
    ,catalog="prepa"
)
public class UtilisateurProfil  implements java.io.Serializable {


     private Integer idutilisateurProfil;
     private String code;
     private String libelle;
     private Boolean voirEmploye;
     private Boolean voirEnseignant;
     private Set<UtilisateurProfilFiliere> utilisateurProfilFilieres = new HashSet<UtilisateurProfilFiliere>(0);
     private Set<Utilisateur> utilisateurs = new HashSet<Utilisateur>(0);

    public UtilisateurProfil() {
    }

    public UtilisateurProfil(String code, String libelle, Boolean voirEmploye, Boolean voirEnseignant, Set<UtilisateurProfilFiliere> utilisateurProfilFilieres, Set<Utilisateur> utilisateurs) {
       this.code = code;
       this.libelle = libelle;
       this.voirEmploye = voirEmploye;
       this.voirEnseignant = voirEnseignant;
       this.utilisateurProfilFilieres = utilisateurProfilFilieres;
       this.utilisateurs = utilisateurs;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idutilisateur_profil", unique=true, nullable=false)
    public Integer getIdutilisateurProfil() {
        return this.idutilisateurProfil;
    }
    
    public void setIdutilisateurProfil(Integer idutilisateurProfil) {
        this.idutilisateurProfil = idutilisateurProfil;
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

    
    @Column(name="voir_employe")
    public Boolean getVoirEmploye() {
        return this.voirEmploye;
    }
    
    public void setVoirEmploye(Boolean voirEmploye) {
        this.voirEmploye = voirEmploye;
    }

    
    @Column(name="voir_enseignant")
    public Boolean getVoirEnseignant() {
        return this.voirEnseignant;
    }
    
    public void setVoirEnseignant(Boolean voirEnseignant) {
        this.voirEnseignant = voirEnseignant;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utilisateurProfil")
    public Set<UtilisateurProfilFiliere> getUtilisateurProfilFilieres() {
        return this.utilisateurProfilFilieres;
    }
    
    public void setUtilisateurProfilFilieres(Set<UtilisateurProfilFiliere> utilisateurProfilFilieres) {
        this.utilisateurProfilFilieres = utilisateurProfilFilieres;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="utilisateurProfil")
    public Set<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }
    
    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }




}


