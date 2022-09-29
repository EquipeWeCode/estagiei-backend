package br.edu.ifsp.estagiei.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.ifsp.estagiei.entity.Candidatura;

public interface CandidaturaRepositoryCustom {
	
	public Candidatura findByIds(Long codEstudante, Long codVaga);
	
	public Page<Candidatura> findCandidaturasByCodEstudante(Long codEstudante, Pageable paginacao);
}
