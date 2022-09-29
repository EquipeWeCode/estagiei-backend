package br.edu.ifsp.estagiei.entity.id;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CandidaturaId implements Serializable {
	
	private static final long serialVersionUID = -5832058774429859727L;

	private Long codEstudante;

	private Long codVaga;

	public CandidaturaId(Long codEstudante, Long codVaga) {
		this.codEstudante = codEstudante;
		this.codVaga = codVaga;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codEstudante, codVaga);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidaturaId other = (CandidaturaId) obj;
		return Objects.equals(codEstudante, other.codEstudante) && Objects.equals(codVaga, other.codVaga);
	}

}
