package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Badge.class)
public abstract class Badge_ {

	public static volatile SingularAttribute<Badge, String> code;
	public static volatile SingularAttribute<Badge, String> libelle;
	public static volatile SingularAttribute<Badge, Integer> idbadge;
	public static volatile SetAttribute<Badge, Entree> entrees;

}

