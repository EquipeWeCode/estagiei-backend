package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Candidatura.class)
public abstract class Candidatura_ {

	public static volatile SingularAttribute<Candidatura, Estudante> estudante;
	public static volatile SingularAttribute<Candidatura, Vaga> vaga;
	public static volatile SingularAttribute<Candidatura, Long> codEstudante;
	public static volatile SingularAttribute<Candidatura, Long> codVaga;

}

