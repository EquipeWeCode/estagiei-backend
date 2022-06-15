package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import br.edu.ifsp.estagiei.dto.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Vaga;

public interface VagaRepositoryCustom {
	public List<Vaga> buscaTodosPorFiltro(VagaFiltroDTO filtro);
	public List<Vaga> buscaVagasRecomendadas(String codEstudante);
}
