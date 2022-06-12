package br.edu.ifsp.estagiei.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import br.edu.ifsp.estagiei.entity.id.CompetenciaVagaId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_comp_vaga")
@Getter
@Setter
@NoArgsConstructor
public class CompetenciaVaga {
	@EmbeddedId
	private CompetenciaVagaId id;

	@ManyToOne
	@MapsId("codCompetencia")
	@JoinColumn(name = "cod_competencia")
	private Competencia competencia;

	@ManyToOne
	@MapsId("codVaga")
	@JoinColumn(name = "cod_vaga")
	private Vaga vaga;
}
