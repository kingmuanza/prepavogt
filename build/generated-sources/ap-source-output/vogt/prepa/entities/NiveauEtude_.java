package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NiveauEtude.class)
public abstract class NiveauEtude_ {

	public static volatile SingularAttribute<NiveauEtude, Integer> idniveauEtude;
	public static volatile SetAttribute<NiveauEtude, Cours> courses;
	public static volatile SingularAttribute<NiveauEtude, String> code;
	public static volatile SingularAttribute<NiveauEtude, Integer> valeur;
	public static volatile SingularAttribute<NiveauEtude, String> libelle;
	public static volatile SetAttribute<NiveauEtude, Etudiant> etudiants;

}

