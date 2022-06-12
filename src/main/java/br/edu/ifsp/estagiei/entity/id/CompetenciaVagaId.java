package br.edu.ifsp.estagiei.entity.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompetenciaVagaId implements Serializable {

	private static final long serialVersionUID = 7444357158171279688L;

	@Column(name = "cod_vaga")
	private Long codVaga;

	@Column(name = "cod_competencia")
	private String codCompetencia;

	@Override
	public int hashCode() {
		return Objects.hash(codCompetencia, codVaga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompetenciaVagaId other = (CompetenciaVagaId) obj;
		return Objects.equals(codCompetencia, other.codCompetencia) && Objects.equals(codVaga, other.codVaga);
	}

}
