package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	public Optional<Pessoa> findByCpf(@Param("cpf") String cpf);
}
