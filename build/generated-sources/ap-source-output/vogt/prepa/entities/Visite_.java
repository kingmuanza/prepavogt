package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Visite.class)
public abstract class Visite_ {

	public static volatile SingularAttribute<Visite, Individu> individu;
	public static volatile SingularAttribute<Visite, Integer> idvisite;
	public static volatile SingularAttribute<Visite, String> nomComplet;
	public static volatile SingularAttribute<Visite, String> motif;
	public static volatile SingularAttribute<Visite, String> commentaire;
	public static volatile SetAttribute<Visite, Entree> entrees;

}

