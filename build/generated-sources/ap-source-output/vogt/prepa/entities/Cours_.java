package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cours.class)
public abstract class Cours_ {

	public static volatile SingularAttribute<Cours, Filiere> filiere;
	public static volatile SetAttribute<Cours, CoursEnseignant> coursEnseignants;
	public static volatile SingularAttribute<Cours, Integer> idcours;
	public static volatile SingularAttribute<Cours, NiveauEtude> niveauEtude;
	public static volatile SingularAttribute<Cours, Matiere> matiere;

}

