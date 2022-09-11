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
	public static volatile SingularAttribute<Estudante, Boolean> indAtivo;
	public static volatile SetAttribute<Estudante, HistoricoEscolar> historicoEscolar;
	public static volatile SetAttribute<Estudante, ExperienciaProfissional> experienciaProfissional;
	public static volatile SingularAttribute<Estudante, Auditoria> auditoria;
	public static volatile SingularAttribute<Estudante, Long> codPessoa;
	public static volatile SetAttribute<Estudante, Competencia> competencias;

	public static final String VAGAS = "vagas";
	public static final String PESSOA = "pessoa";
	public static final String COD_ESTUDANTE = "codEstudante";
	public static final String IND_ATIVO = "indAtivo";
	public static final String HISTORICO_ESCOLAR = "historicoEscolar";
	public static final String EXPERIENCIA_PROFISSIONAL = "experienciaProfissional";
	public static final String AUDITORIA = "auditoria";
	public static final String COD_PESSOA = "codPessoa";
	public static final String COMPETENCIAS = "competencias";

}

