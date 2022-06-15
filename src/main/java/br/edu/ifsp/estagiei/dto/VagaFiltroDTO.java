package br.edu.ifsp.estagiei.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VagaFiltroDTO extends FiltroDTO {
	private String titulo;
	private String descricao;
	
	public String getTituloFiltro() {
		return getStringFiltro(getTitulo());
	}
	public String getDescricaoFiltro() {
		return getStringFiltro(getDescricao());
	}
	public Boolean hasDescricao() {
		return descricao != null && !descricao.isEmpty();
	}
	public Boolean hasTitulo() {
		return titulo != null && !titulo.isEmpty();
	}
}
