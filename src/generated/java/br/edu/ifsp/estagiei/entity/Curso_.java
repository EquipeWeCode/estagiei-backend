package br.edu.ifsp.estagiei.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, String> tipoCurso;
	public static volatile SingularAttribute<Curso, Long> codCurso;
	public static volatile SingularAttribute<Curso, Boolean> indAtivo;
	public static volatile SingularAttribute<Curso, String> descricao;

	public static final String TIPO_CURSO = "tipoCurso";
	public static final String COD_CURSO = "codCurso";
	public static final String IND_ATIVO = "indAtivo";
	public static final String DESCRICAO = "descricao";

}

