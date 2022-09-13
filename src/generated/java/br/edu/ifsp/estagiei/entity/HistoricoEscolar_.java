package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistoricoEscolar.class)
public abstract class HistoricoEscolar_ {

	public static volatile SingularAttribute<HistoricoEscolar, String> instEnsino;
	public static volatile SingularAttribute<HistoricoEscolar, LocalDate> dataFim;
	public static volatile SingularAttribute<HistoricoEscolar, String> curso;
	public static volatile SingularAttribute<HistoricoEscolar, Long> codHistEscolar;
	public static volatile SingularAttribute<HistoricoEscolar, LocalDate> dataInicio;
	public static volatile SingularAttribute<HistoricoEscolar, String> nvlEscolaridade;
	public static volatile SingularAttribute<HistoricoEscolar, Auditoria> auditoria;
	public static volatile SingularAttribute<HistoricoEscolar, Estudante> estudante;
	public static volatile SingularAttribute<HistoricoEscolar, String> status;

	public static final String INST_ENSINO = "instEnsino";
	public static final String DATA_FIM = "dataFim";
	public static final String CURSO = "curso";
	public static final String COD_HIST_ESCOLAR = "codHistEscolar";
	public static final String DATA_INICIO = "dataInicio";
	public static final String NVL_ESCOLARIDADE = "nvlEscolaridade";
	public static final String AUDITORIA = "auditoria";
	public static final String ESTUDANTE = "estudante";
	public static final String STATUS = "status";

}

