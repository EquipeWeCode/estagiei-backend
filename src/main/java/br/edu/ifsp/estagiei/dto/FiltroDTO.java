package br.edu.ifsp.estagiei.dto;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FiltroDTO {
	@Parameter(hidden = true)
	private Long quantidadeTotal;
	
	public String getStringFiltro(String valor) {
		return "%" + Optional.ofNullable(valor).map(String::toUpperCase).orElse("") + "%";
	}
	
	public String getStringFiltroSemPorcentagem(String valor) {
		return Optional.ofNullable(valor).map(String::toUpperCase).orElse("");
	}
}
