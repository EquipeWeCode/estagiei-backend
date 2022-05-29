package br.edu.ifsp.estagiei.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {}