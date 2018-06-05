package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Enseignant.class)
public abstract class Enseignant_ {

	public static volatile SingularAttribute<Enseignant, Integer> idenseignant;
	public static volatile SetAttribute<Enseignant, CoursEnseignant> coursEnseignants;
	public static volatile SingularAttribute<Enseignant, Integer> idindividu;

}

