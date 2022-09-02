package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudante.class)
public abstract class Estudante_ {

	public static volatile SetAttribute<Estudante, Vaga> vagas;
	public static volatile SingularAttribute<Estudante, Pessoa> pessoa;
	public static volatile SingularAttribute<Estudante, Long> codEstudante;
	public static volatile SingularAttribute<Estudante, String> instEnsino;
	public static volatile SingularAttribute<Estudante, Boolean> indAtivo;
	public static volatile SetAttribute<Estudante, Competencia> competencias;

	public static final String VAGAS = "vagas";
	public static final String PESSOA = "pessoa";
	public static final String COD_ESTUDANTE = "codEstudante";
	public static final String IND_ATIVO = "indAtivo";
	public static final String COMPETENCIAS = "competencias";

}

