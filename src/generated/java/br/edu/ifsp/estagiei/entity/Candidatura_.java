package br.edu.ifsp.estagiei.entity;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Candidatura.class)
public abstract class Candidatura_ {

	public static volatile SingularAttribute<Candidatura, Long> codVaga;
	public static volatile SingularAttribute<Candidatura, Vaga> vaga;
	public static volatile SingularAttribute<Candidatura, Long> codEstudante;
	public static volatile SingularAttribute<Candidatura, Estudante> estudante;
	public static volatile SingularAttribute<Candidatura, CandidaturaEnum> status;

	public static final String COD_VAGA = "codVaga";
	public static final String VAGA = "vaga";
	public static final String COD_ESTUDANTE = "codEstudante";
	public static final String ESTUDANTE = "estudante";
	public static final String STATUS = "status";

}

