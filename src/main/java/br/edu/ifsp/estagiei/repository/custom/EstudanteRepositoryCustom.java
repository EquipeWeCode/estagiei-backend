package br.edu.ifsp.estagiei.repository.custom;

import java.util.List;

import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.entity.Estudante;

public interface EstudanteRepositoryCustom {
	public Estudante findByCodEstudante(Long codUsuario);
	public List<Estudante> buscaTodosPorFiltro(EstudanteFiltroDTO filtro);
}
