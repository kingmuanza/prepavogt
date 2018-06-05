package vogt.prepa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Entree.class)
public abstract class Entree_ {

	public static volatile SingularAttribute<Entree, Badge> badge;
	public static volatile SingularAttribute<Entree, Individu> individu;
	public static volatile SingularAttribute<Entree, Visite> visite;
	public static volatile SingularAttribute<Entree, Date> dateSortie;
	public static volatile SingularAttribute<Entree, Integer> identree;
	public static volatile SingularAttribute<Entree, String> nomComplet;
	public static volatile SingularAttribute<Entree, String> motif;
	public static volatile SingularAttribute<Entree, Date> dateEntree;
	public static volatile SingularAttribute<Entree, String> commentaire;

}

