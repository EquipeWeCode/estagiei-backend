package br.edu.ifsp.estagiei.dto;

import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FiltroDTO {
	@Schema(hidden = true)
	private Long quantidadeTotal;
	
	public String getStringFiltro(String valor) {
		return "%" + Optional.ofNullable(valor).map(String::toUpperCase).orElse("") + "%";
	}
}
