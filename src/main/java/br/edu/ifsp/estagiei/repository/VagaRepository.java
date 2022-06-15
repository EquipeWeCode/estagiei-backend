package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.repository.custom.VagaRepositoryCustom;

public interface VagaRepository extends CrudRepository<Vaga, Long>, VagaRepositoryCustom {
}
