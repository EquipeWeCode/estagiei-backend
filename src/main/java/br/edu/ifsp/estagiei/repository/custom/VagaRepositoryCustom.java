package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Vaga;

public interface VagaRepositoryCustom {
	public Vaga buscaVagaPorId(Long codVaga);
	public List<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro);
	public List<Vaga> buscaVagasRecomendadas(Long codEstudante);
}
