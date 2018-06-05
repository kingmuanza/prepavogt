package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CoursEnseignant.class)
public abstract class CoursEnseignant_ {

	public static volatile SingularAttribute<CoursEnseignant, Integer> idcoursEnseignant;
	public static volatile SingularAttribute<CoursEnseignant, Enseignant> enseignant;
	public static volatile SingularAttribute<CoursEnseignant, Cours> cours;

}

