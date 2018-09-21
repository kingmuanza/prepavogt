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
 * PersonneAutorisee generated by hbm2java
 */
@Entity
@Table(name="personne_autorisee"
    ,catalog="prepa"
)
public class PersonneAutorisee  implements java.io.Serializable {


     private Integer idpersonneAutorisee;
     private Autorisation autorisation;
     private Individu individu;

    public PersonneAutorisee() {
    }

    public PersonneAutorisee(Autorisation autorisation, Individu individu) {
       this.autorisation = autorisation;
       this.individu = individu;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idpersonne_autorisee", unique=true, nullable=false)
    public Integer getIdpersonneAutorisee() {
        return this.idpersonneAutorisee;
    }
    
    public void setIdpersonneAutorisee(Integer idpersonneAutorisee) {
        this.idpersonneAutorisee = idpersonneAutorisee;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idautorisation")
    public Autorisation getAutorisation() {
        return this.autorisation;
    }
    
    public void setAutorisation(Autorisation autorisation) {
        this.autorisation = autorisation;
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


