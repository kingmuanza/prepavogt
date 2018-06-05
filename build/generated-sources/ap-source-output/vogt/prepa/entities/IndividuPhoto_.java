package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(IndividuPhoto.class)
public abstract class IndividuPhoto_ {

	public static volatile SingularAttribute<IndividuPhoto, byte[]> src;
	public static volatile SingularAttribute<IndividuPhoto, Integer> idindividuPhoto;
	public static volatile SetAttribute<IndividuPhoto, Individu> individus;
	public static volatile SingularAttribute<IndividuPhoto, String> statut;

}

