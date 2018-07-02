package vogt.prepa.entities;
// Generated 2 juil. 2018 10:52:12 by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Pointage generated by hbm2java
 */
@Entity
@Table(name="pointage"
    ,catalog="prepa"
)
public class Pointage  implements java.io.Serializable {


     private Integer idpointage;
     private String numero;
     private String machine;
     private String matricule;
     private String mode;
     private String iomd;
     private Date heure;

    public Pointage() {
    }

    public Pointage(String numero, String machine, String matricule, String mode, String iomd, Date heure) {
       this.numero = numero;
       this.machine = machine;
       this.matricule = matricule;
       this.mode = mode;
       this.iomd = iomd;
       this.heure = heure;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idpointage", unique=true, nullable=false)
    public Integer getIdpointage() {
        return this.idpointage;
    }
    
    public void setIdpointage(Integer idpointage) {
        this.idpointage = idpointage;
    }

    
    @Column(name="numero", length=45)
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    @Column(name="machine", length=45)
    public String getMachine() {
        return this.machine;
    }
    
    public void setMachine(String machine) {
        this.machine = machine;
    }

    
    @Column(name="matricule", length=45)
    public String getMatricule() {
        return this.matricule;
    }
    
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    @Column(name="mode", length=45)
    public String getMode() {
        return this.mode;
    }
    
    public void setMode(String mode) {
        this.mode = mode;
    }

    
    @Column(name="iomd", length=45)
    public String getIomd() {
        return this.iomd;
    }
    
    public void setIomd(String iomd) {
        this.iomd = iomd;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="heure", length=19)
    public Date getHeure() {
        return this.heure;
    }
    
    public void setHeure(Date heure) {
        this.heure = heure;
    }




}


