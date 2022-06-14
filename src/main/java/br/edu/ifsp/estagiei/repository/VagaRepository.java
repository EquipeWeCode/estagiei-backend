package br.edu.ifsp.estagiei.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Vaga;

public interface VagaRepository extends CrudRepository<Vaga, Long> {
	@Query("FROM Vaga v LEFT JOIN FETCH v.competencias")
	public Iterable<Vaga> findAll();
}
