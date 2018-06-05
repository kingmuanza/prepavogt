package vogt.prepa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Individu.class)
public abstract class Individu_ {

	public static volatile SingularAttribute<Individu, String> tel1;
	public static volatile SetAttribute<Individu, Utilisateur> utilisateurs;
	public static volatile SingularAttribute<Individu, String> tel2;
	public static volatile SingularAttribute<Individu, String> matricule;
	public static volatile SetAttribute<Individu, Visite> visites;
	public static volatile SetAttribute<Individu, Etudiant> etudiants;
	public static volatile SingularAttribute<Individu, Integer> idindividu;
	public static volatile SetAttribute<Individu, Entree> entrees;
	public static volatile SingularAttribute<Individu, String> prenoms;
	public static volatile SingularAttribute<Individu, String> noms;
	public static volatile SingularAttribute<Individu, IndividuPhoto> individuPhoto;
	public static volatile SingularAttribute<Individu, Boolean> genre;
	public static volatile SingularAttribute<Individu, String> lieunaiss;
	public static volatile SingularAttribute<Individu, String> residence;
	public static volatile SingularAttribute<Individu, Date> datenaiss;
	public static volatile SingularAttribute<Individu, String> email;
	public static volatile SetAttribute<Individu, Employe> employes;
	public static volatile SingularAttribute<Individu, String> civilite;

}

