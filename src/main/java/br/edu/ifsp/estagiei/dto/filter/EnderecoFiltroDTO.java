package br.edu.ifsp.estagiei.dto.filter;

import br.edu.ifsp.estagiei.dto.FiltroDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoFiltroDTO extends FiltroDTO {

	private Integer cep;
	private String bairro;
	private String cidade;
	private String estado;
	
	public String getBairroFiltro() {
		return getStringFiltro(getBairro());
	}
	
	public String getCidadeFiltro() {
		return getStringFiltro(getCidade());
	}
	
	public String getEstadoFiltro() {
		return getStringFiltro(getEstado());
	}
	
	public Boolean hasCep() {
		return cep != null;
	}

	public Boolean hasBairro() {
		return bairro != null;
	}

	public Boolean hasCidade() {
		return cidade != null;
	}

	public Boolean hasEstado() {
		return estado != null;
	}

}
