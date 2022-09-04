package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByEmpresaCnpj(String cnpj);
}