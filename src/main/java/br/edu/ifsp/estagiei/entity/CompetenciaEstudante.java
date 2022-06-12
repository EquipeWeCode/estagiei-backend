package br.edu.ifsp.estagiei.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.entity.id.CompetenciaEstudanteId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_comp_estud")
@Getter
@Setter
@NoArgsConstructor
public class CompetenciaEstudante {

	@EmbeddedId
	private CompetenciaEstudanteId id;

	@ManyToOne
	@MapsId("codCompetencia")
	@JoinColumn(name = "cod_competencia")
	private Competencia competencia;

	@ManyToOne
	@MapsId("codEstudante")
	@JoinColumn(name = "cod_estudante")
	private Estudante estudante;

	@Column(name = "ind_ativo", columnDefinition = "BOOLEAN DEFAULT 'TRUE'", nullable = false)
	private Boolean indAtivo = true;
}
