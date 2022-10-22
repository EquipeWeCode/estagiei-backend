package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Vaga;

public interface VagaRepositoryCustom {
	public Vaga buscaVagaPorId(Long codVaga);
	public Page<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro, Pageable paginacao);
	public List<Long> buscaVagasRecomendadas(Long codEstudante);
}
