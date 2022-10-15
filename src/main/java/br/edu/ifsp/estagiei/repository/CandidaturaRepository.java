package br.edu.ifsp.estagiei.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.repository.custom.CandidaturaRepositoryCustom;

public interface CandidaturaRepository extends CrudRepository<Candidatura, Long>,
		PagingAndSortingRepository<Candidatura, Long>, CandidaturaRepositoryCustom {
	public List<Candidatura> findByCodVaga(@Param("codVaga") Long codVaga);
}
