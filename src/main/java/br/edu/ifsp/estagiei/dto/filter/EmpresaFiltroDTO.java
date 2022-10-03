package br.edu.ifsp.estagiei.dto.filter;

import br.edu.ifsp.estagiei.dto.FiltroDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmpresaFiltroDTO extends FiltroDTO {

	private Long codEmpresa;
	private String nomeFantasia;
	private String cnpj;
	private Boolean indAtivo;
	private Integer cep;
	private String bairro;
	private String cidade;
	private String estado;

	public String getNomeFiltro() {
		return getStringFiltro(getNomeFantasia());
	}

	public String getBairroFiltro() {
		return getStringFiltro(getBairro());
	}

	public String getCidadeFiltro() {
		return getStringFiltro(getCidade());
	}

	public String getEstadoFiltro() {
		return getStringFiltro(getEstado());
	}

	public Boolean hasCodEmpresa() {
		return codEmpresa != null;
	}

	public Boolean hasNomeFantasia() {
		return nomeFantasia != null;
	}
	
	public Boolean hasIndAtivo() {
		return indAtivo != null;
	}

	public Boolean hasCnpj() {
		return cnpj != null;
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
	
	public Boolean hasFiltroEndereco() {
		return hasCep() || hasBairro() || hasCidade() || hasEstado();
	}

}
