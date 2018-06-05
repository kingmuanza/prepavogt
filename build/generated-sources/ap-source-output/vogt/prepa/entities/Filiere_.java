package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Filiere.class)
public abstract class Filiere_ {

	public static volatile SetAttribute<Filiere, Cours> courses;
	public static volatile SingularAttribute<Filiere, String> code;
	public static volatile SingularAttribute<Filiere, String> libelle;
	public static volatile SetAttribute<Filiere, Etudiant> etudiants;
	public static volatile SetAttribute<Filiere, UtilisateurProfilFiliere> utilisateurProfilFilieres;
	public static volatile SingularAttribute<Filiere, Integer> idfiliere;

}

