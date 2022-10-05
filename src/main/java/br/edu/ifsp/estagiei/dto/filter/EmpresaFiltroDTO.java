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
public class EmpresaFiltroDTO extends FiltroDTO {

	private Long codEmpresa;
	private String nomeFantasia;
	private String cnpj;
	private Boolean indAtivo;
	private String cep;
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
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}
	
	public Boolean hasIndAtivo() {
		return indAtivo != null;
	}

	public Boolean hasCnpj() {
		return cnpj != null;
	}

	public Boolean hasCep() {
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}

	public Boolean hasBairro() {
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}

	public Boolean hasCidade() {
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}

	public Boolean hasEstado() {
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}
	
	public Boolean hasFiltroEndereco() {
		return hasCep() || hasBairro() || hasCidade() || hasEstado();
	}

}
