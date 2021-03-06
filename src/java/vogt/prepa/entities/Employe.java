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
 * Employe generated by hbm2java
 */
@Entity
@Table(name="employe"
    ,catalog="prepa"
)
public class Employe  implements java.io.Serializable {


     private Integer idemploye;
     private Individu individu;
     private Poste poste;

    public Employe() {
    }

    public Employe(Individu individu, Poste poste) {
       this.individu = individu;
       this.poste = poste;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idemploye", unique=true, nullable=false)
    public Integer getIdemploye() {
        return this.idemploye;
    }
    
    public void setIdemploye(Integer idemploye) {
        this.idemploye = idemploye;
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
    @JoinColumn(name="idposte")
    public Poste getPoste() {
        return this.poste;
    }
    
    public void setPoste(Poste poste) {
        this.poste = poste;
    }




}


