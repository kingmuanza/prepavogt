package vogt.prepa.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CoursEnseignantDate.class)
public abstract class CoursEnseignantDate_ {

	public static volatile SingularAttribute<CoursEnseignantDate, Integer> idcoursEnseignant;
	public static volatile SingularAttribute<CoursEnseignantDate, Integer> idcoursEnseignantDate;
	public static volatile SingularAttribute<CoursEnseignantDate, Date> heureDebut;
	public static volatile SingularAttribute<CoursEnseignantDate, Integer> jourSemaine;
	public static volatile SingularAttribute<CoursEnseignantDate, Date> heureFin;

}

