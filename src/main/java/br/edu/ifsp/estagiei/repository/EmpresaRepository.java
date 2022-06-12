package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {}