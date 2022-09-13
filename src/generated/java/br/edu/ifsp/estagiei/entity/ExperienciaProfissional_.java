package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExperienciaProfissional.class)
public abstract class ExperienciaProfissional_ {

	public static volatile SingularAttribute<ExperienciaProfissional, String> nomeEmpresa;
	public static volatile SingularAttribute<ExperienciaProfissional, Endereco> endereco;
	public static volatile SingularAttribute<ExperienciaProfissional, LocalDate> dataFim;
	public static volatile SingularAttribute<ExperienciaProfissional, Long> codExpProfissional;
	public static volatile SingularAttribute<ExperienciaProfissional, LocalDate> dataInicio;
	public static volatile SingularAttribute<ExperienciaProfissional, String> cargo;
	public static volatile SingularAttribute<ExperienciaProfissional, Auditoria> auditoria;
	public static volatile SingularAttribute<ExperienciaProfissional, Estudante> estudante;
	public static volatile SingularAttribute<ExperienciaProfissional, String> descricao;

	public static final String NOME_EMPRESA = "nomeEmpresa";
	public static final String ENDERECO = "endereco";
	public static final String DATA_FIM = "dataFim";
	public static final String COD_EXP_PROFISSIONAL = "codExpProfissional";
	public static final String DATA_INICIO = "dataInicio";
	public static final String CARGO = "cargo";
	public static final String AUDITORIA = "auditoria";
	public static final String ESTUDANTE = "estudante";
	public static final String DESCRICAO = "descricao";

}

