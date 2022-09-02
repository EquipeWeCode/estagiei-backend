package br.edu.ifsp.estagiei.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_hist_escolar")
public class HistoricoEscolar {

	@Id
	@SequenceGenerator(name = "tb_hist_escolar_cod_hist_escolar_seq", sequenceName = "tb_hist_escolar_cod_hist_escolar_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_empresa_cod_empresa_seq")
	@Column(name = "cod_hist_escolar", updatable = false)
	private Long codHistEscolar;
	@Column(name = "curso")
	private String curso;
	@Column(name = "nvl_escolaridade")
	private String nvlEscolaridade;
	@Column(name = "inst_ensino")
	private String instEnsino;
	@Column(name = "data_inicio")
	private Timestamp dataInicio;
	@Column(name = "data_fim")
	private Timestamp dataFim;
	@Column(name = "status")
	private String status;
	@Embedded
	private Auditoria auditoria;

	@OneToOne(mappedBy = "historicoEscolar", fetch = FetchType.LAZY)
	private Estudante estudante;
}
