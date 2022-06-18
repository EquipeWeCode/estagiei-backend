package br.edu.ifsp.estagiei.dto.filter;

import java.util.List;

import br.edu.ifsp.estagiei.dto.FiltroDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VagaFiltroDTO extends FiltroDTO {
	private String titulo;
	private String descricao;
	private List<Long> ids;
	
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
	public Boolean hasIds() {
		return ids != null && ids.size()>0;
	}
}
