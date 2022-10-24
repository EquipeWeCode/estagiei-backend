package br.edu.ifsp.estagiei.dto.filter;

import org.springdoc.api.annotations.ParameterObject;

import br.edu.ifsp.estagiei.dto.FiltroDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ParameterObject
public class CandidaturaFiltroDTO extends FiltroDTO {
	private Long codEstudante;
	private Boolean indAtivo;

	public boolean isAtivo() {
		return this.indAtivo != null && indAtivo;
	}
	
	public boolean isNotAtivo() {
		return this.indAtivo != null && !indAtivo;
	}
}
