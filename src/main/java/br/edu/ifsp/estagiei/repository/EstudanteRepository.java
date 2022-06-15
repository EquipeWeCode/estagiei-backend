package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.repository.custom.EstudanteRepositoryCustom;

public interface EstudanteRepository extends CrudRepository<Estudante, String>, EstudanteRepositoryCustom {}
