package vogt.prepa.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, Integer> idutilisateur;
	public static volatile SingularAttribute<Utilisateur, Individu> individu;
	public static volatile SingularAttribute<Utilisateur, String> login;
	public static volatile SingularAttribute<Utilisateur, String> passe;
	public static volatile SingularAttribute<Utilisateur, UtilisateurProfil> utilisateurProfil;

}

