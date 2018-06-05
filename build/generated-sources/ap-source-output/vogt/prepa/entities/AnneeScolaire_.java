package vogt.prepa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AnneeScolaire.class)
public abstract class AnneeScolaire_ {

	public static volatile SingularAttribute<AnneeScolaire, Integer> idanneeScolaire;
	public static volatile SingularAttribute<AnneeScolaire, String> code;
	public static volatile SingularAttribute<AnneeScolaire, Date> dateDebut;
	public static volatile SingularAttribute<AnneeScolaire, String> libelle;
	public static volatile SingularAttribute<AnneeScolaire, Date> dateFin;
	public static volatile SetAttribute<AnneeScolaire, Etudiant> etudiants;

}

