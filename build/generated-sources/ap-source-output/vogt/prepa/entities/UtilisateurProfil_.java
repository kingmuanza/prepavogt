package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UtilisateurProfil.class)
public abstract class UtilisateurProfil_ {

	public static volatile SingularAttribute<UtilisateurProfil, Boolean> voirEmploye;
	public static volatile SetAttribute<UtilisateurProfil, Utilisateur> utilisateurs;
	public static volatile SingularAttribute<UtilisateurProfil, String> code;
	public static volatile SingularAttribute<UtilisateurProfil, Integer> idutilisateurProfil;
	public static volatile SingularAttribute<UtilisateurProfil, String> libelle;
	public static volatile SetAttribute<UtilisateurProfil, UtilisateurProfilFiliere> utilisateurProfilFilieres;
	public static volatile SingularAttribute<UtilisateurProfil, Boolean> voirEnseignant;

}

