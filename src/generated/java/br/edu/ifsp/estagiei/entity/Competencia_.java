package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Competencia.class)
public abstract class Competencia_ {

	public static volatile SingularAttribute<Competencia, Long> codCompetencia;
	public static volatile SetAttribute<Competencia, Vaga> vagas;
	public static volatile SingularAttribute<Competencia, Boolean> indAtivo;
	public static volatile SetAttribute<Competencia, Estudante> estudantes;
	public static volatile SingularAttribute<Competencia, Auditoria> auditoria;
	public static volatile SingularAttribute<Competencia, String> descricao;

	public static final String COD_COMPETENCIA = "codCompetencia";
	public static final String VAGAS = "vagas";
	public static final String IND_ATIVO = "indAtivo";
	public static final String ESTUDANTES = "estudantes";
	public static final String AUDITORIA = "auditoria";
	public static final String DESCRICAO = "descricao";

}

