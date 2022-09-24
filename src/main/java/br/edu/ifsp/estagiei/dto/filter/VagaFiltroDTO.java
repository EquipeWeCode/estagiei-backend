package br.edu.ifsp.estagiei.dto.filter;

import java.util.List;

import org.springdoc.api.annotations.ParameterObject;

import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
import br.edu.ifsp.estagiei.dto.FiltroDTO;
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
	private Integer cep;
	private String bairro;
	private String cidade;
	private String estado;
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

	public Boolean hasDescricao() {
		return descricao != null && !descricao.isEmpty();
	}

	public Boolean hasTitulo() {
		return titulo != null && !titulo.isEmpty();
	}

	public Boolean hasCurso() {
		return curso != null && !curso.isEmpty();
	}

	public Boolean hasModalidade() {
		return modalidade != null;
	}

	public Boolean hasIds() {
		return ids != null && ids.size() > 0;
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
	
	public Boolean hasCodEmpresa() {
		return codEmpresa != null;
	}
}
