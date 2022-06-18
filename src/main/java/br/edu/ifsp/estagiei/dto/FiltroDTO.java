package br.edu.ifsp.estagiei.dto;

import java.util.Optional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FiltroDTO {
	private String ordem;
	
	public String getOrdemFiltro() {
		return getStringFiltro(getOrdem());
	}
	public String getStringFiltro(String valor) {
		return "%" + Optional.ofNullable(valor).map(String::toUpperCase).orElse("") + "%";
	}
	public Boolean hasOrdem() {
		return ordem != null && !ordem.isEmpty();
	}
}
