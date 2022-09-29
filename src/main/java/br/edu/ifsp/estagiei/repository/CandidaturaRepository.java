package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.repository.custom.CandidaturaRepositoryCustom;

public interface CandidaturaRepository extends CrudRepository<Candidatura, Long>, PagingAndSortingRepository<Candidatura, Long>, CandidaturaRepositoryCustom {

}
