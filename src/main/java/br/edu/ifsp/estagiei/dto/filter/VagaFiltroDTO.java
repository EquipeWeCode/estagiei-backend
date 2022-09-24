package br.edu.ifsp.estagiei.dto.filter;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;

import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
import br.edu.ifsp.estagiei.dto.FiltroDTO;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ParameterObject
public class VagaFiltroDTO extends FiltroDTO {
	private String titulo;
	private String descricao;
	private String curso;
	private ModalidadeEnum modalidade;
	private List<Long> ids;
	private String cep;
	private String bairro;
	private String cidade;
	private String estado;
	private String nomeEmpresa;
	private Long codEmpresa;

	public String getTituloFiltro() {
		return getStringFiltro(getTitulo());
	}

	public String getDescricaoFiltro() {
		return getStringFiltro(getDescricao());
	}
	
	public String getCursoFiltro() {
		return getStringFiltro(getCurso());
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
	
	public String getNomeEmpresaFiltro() {
		return getStringFiltro(getNomeEmpresa());
	}

	public Boolean hasDescricao() {
		return EstagieiUtils.isNotEmptyOrNull(descricao);
	}

	public Boolean hasTitulo() {
		return EstagieiUtils.isNotEmptyOrNull(titulo);
	}

	public Boolean hasCurso() {
		return EstagieiUtils.isNotEmptyOrNull(curso);
	}

	public Boolean hasModalidade() {
		return modalidade != null;
	}
	
	public Boolean hasNomeEmpresa() {
		return EstagieiUtils.isNotEmptyOrNull(nomeEmpresa);
	}

	public Boolean hasIds() {
		return ids != null && ids.size() > 0;
	}

	public Boolean hasCep() {
		return EstagieiUtils.isNotEmptyOrNull(cep);
	}

	public Boolean hasBairro() {
		return EstagieiUtils.isNotEmptyOrNull(bairro);
	}

	public Boolean hasCidade() {
		return EstagieiUtils.isNotEmptyOrNull(cidade);
	}

	public Boolean hasEstado() {
		return EstagieiUtils.isNotEmptyOrNull(estado);
	}
	
	public Boolean hasCodEmpresa() {
		return codEmpresa != null;
	}
}
