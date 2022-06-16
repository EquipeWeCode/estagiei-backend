package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {}
