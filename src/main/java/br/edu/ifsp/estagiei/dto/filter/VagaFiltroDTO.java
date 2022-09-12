package br.edu.ifsp.estagiei.dto.filter;

import java.util.List;

import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
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
	private String curso;
	private ModalidadeEnum modalidade;
	private List<Long> ids;
	private EnderecoFiltroDTO endereco;

	public String getTituloFiltro() {
		return getStringFiltro(getTitulo());
	}

	public String getDescricaoFiltro() {
		return getStringFiltro(getDescricao());
	}
	
	public String getCursoFiltro() {
		return getStringFiltro(getCurso());
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

	public Boolean hasEndereco() {
		return endereco != null;
	}
}
