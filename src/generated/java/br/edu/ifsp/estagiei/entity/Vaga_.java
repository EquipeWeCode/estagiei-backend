package br.edu.ifsp.estagiei.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.edu.ifsp.estagiei.constants.ModalidadeEnum;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vaga.class)
public abstract class Vaga_ {

	public static volatile SingularAttribute<Vaga, Long> codVaga;
	public static volatile SingularAttribute<Vaga, Long> codEmpresa;
	public static volatile SingularAttribute<Vaga, Endereco> endereco;
	public static volatile SingularAttribute<Vaga, Empresa> empresa;
	public static volatile SetAttribute<Vaga, Estudante> estudantes;
	public static volatile SetAttribute<Vaga, Competencia> competencias;
	public static volatile SingularAttribute<Vaga, String> curso;
	public static volatile SingularAttribute<Vaga, String> titulo;
	public static volatile SingularAttribute<Vaga, String> descricao;
	public static volatile SingularAttribute<Vaga, BigDecimal> salario;
	public static volatile SingularAttribute<Vaga, ModalidadeEnum> modalidade;
	public static volatile SingularAttribute<Vaga, Auditoria> auditoria;
	public static volatile SingularAttribute<Vaga, Timestamp> dataInclusao;
	public static volatile SingularAttribute<Vaga, Boolean> indAtivo;

	public static final String COD_VAGA = "codVaga";
	public static final String ENDERECO = "endereco";
	public static final String COD_EMPRESA = "codEmpresa";
	public static final String SALARIO = "salario";
	public static final String IND_ATIVO = "indAtivo";
	public static final String ESTUDANTES = "estudantes";
	public static final String TITULO = "titulo";
	public static final String AUDITORIA = "auditoria";
	public static final String EMPRESA = "empresa";
	public static final String COMPETENCIAS = "competencias";
	public static final String DESCRICAO = "descricao";

}

