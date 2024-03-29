package br.edu.ifsp.estagiei.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.estagiei.dto.filter.CandidaturaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Candidatura;

public interface CandidaturaRepositoryCustom {
	
	public Candidatura findByIds(Long codEstudante, Long codVaga);
	
	public Page<Candidatura> findCandidaturas(CandidaturaFiltroDTO filtro, Pageable paginacao);
}
