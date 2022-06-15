package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estudante.class)
public abstract class Estudante_ {

	public static volatile SingularAttribute<Estudante, String> nvlEnsino;
	public static volatile SingularAttribute<Estudante, String> expProfissional;
	public static volatile SingularAttribute<Estudante, Pessoa> pessoa;
	public static volatile SingularAttribute<Estudante, String> codEstudante;
	public static volatile SingularAttribute<Estudante, String> instEnsino;
	public static volatile SingularAttribute<Estudante, Boolean> indAtivo;
	public static volatile SetAttribute<Estudante, Competencia> competencias;

	public static final String NVL_ENSINO = "nvlEnsino";
	public static final String EXP_PROFISSIONAL = "expProfissional";
	public static final String PESSOA = "pessoa";
	public static final String COD_ESTUDANTE = "codEstudante";
	public static final String INST_ENSINO = "instEnsino";
	public static final String IND_ATIVO = "indAtivo";
	public static final String COMPETENCIAS = "competencias";

}

