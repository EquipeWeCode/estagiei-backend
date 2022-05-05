package br.edu.ifsp.estagiei.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Estudante;

public interface StudentRepository extends CrudRepository<Estudante, String> 
{}
