package br.edu.ifsp.estagiei.entity.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompetenciaEstudanteId implements Serializable {

	private static final long serialVersionUID = 7513153449098099472L;

	@Column(name = "cod_estudante")
	private String codEstudante;

	@Column(name = "cod_competencia")
	private String codCompetencia;

	@Override
	public int hashCode() {
		return Objects.hash(codCompetencia, codEstudante);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompetenciaEstudanteId other = (CompetenciaEstudanteId) obj;
		return Objects.equals(codCompetencia, other.codCompetencia) && Objects.equals(codEstudante, other.codEstudante);
	}

}
