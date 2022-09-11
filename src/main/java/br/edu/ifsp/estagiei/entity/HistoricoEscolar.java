package br.edu.ifsp.estagiei.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_hist_escolar_cod_hist_escolar_seq")
	@Column(name = "cod_hist_escolar", updatable = false)
	private Long codHistEscolar;
	@Column(name = "curso")
	private String curso;
	@Column(name = "nvl_escolaridade")
	private String nvlEscolaridade;
	@Column(name = "inst_ensino")
	private String instEnsino;
	@Column(name = "data_inicio")
	private LocalDate dataInicio;
	@Column(name = "data_fim")
	private LocalDate dataFim;
	@Column(name = "status")
	private String status;
	@Embedded
	private Auditoria auditoria;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "cod_estudante", referencedColumnName = "cod_estudante")
	private Estudante estudante;
	
	@Override
	public int hashCode() {
		return Objects.hash(codHistEscolar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HistoricoEscolar))
			return false;
		HistoricoEscolar other = (HistoricoEscolar) obj;
		if(other.codHistEscolar == null) {
			return false;
		}
		return Objects.equals(codHistEscolar, other.codHistEscolar);
	}
}
