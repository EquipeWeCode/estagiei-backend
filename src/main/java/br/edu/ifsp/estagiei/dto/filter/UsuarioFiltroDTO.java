package br.edu.ifsp.estagiei.dto.filter;

import org.springdoc.api.annotations.ParameterObject;

import br.edu.ifsp.estagiei.dto.FiltroDTO;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ParameterObject
public class UsuarioFiltroDTO extends FiltroDTO {
	private String email;

	public String getEmailFiltro() {
		return getStringFiltroSemPorcentagem(getEmail());
	}

	public Boolean hasEmail() {
		return EstagieiUtils.isNotEmptyOrNull(email);
	}
}
