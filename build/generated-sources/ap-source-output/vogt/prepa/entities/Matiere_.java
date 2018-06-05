package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Matiere.class)
public abstract class Matiere_ {

	public static volatile SetAttribute<Matiere, Cours> courses;
	public static volatile SingularAttribute<Matiere, String> code;
	public static volatile SingularAttribute<Matiere, String> libelle;
	public static volatile SingularAttribute<Matiere, Integer> idmatiere;

}

