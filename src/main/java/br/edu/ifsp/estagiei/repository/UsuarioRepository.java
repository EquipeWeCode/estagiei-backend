package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	public Optional<Usuario> findByEmail(@Param("email") String email);
	public Optional<Usuario> findByEmpresaCnpj(@Param("cnpj") String cnpj);
	@Query("SELECT DISTINCT u FROM Usuario u "
			+ "LEFT JOIN FETCH u.pessoa p "
			+ "LEFT JOIN FETCH p.estudante e "
			+ "LEFT JOIN FETCH u.empresa WHERE u.codUsuario = :codUsuario")
	public Optional<Usuario> findByCodUsuario(@Param("codUsuario") Long codUsuario);
}