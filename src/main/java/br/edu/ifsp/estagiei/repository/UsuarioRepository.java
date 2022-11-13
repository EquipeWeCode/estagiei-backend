package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.repository.custom.UsuarioRepositoryCustom;

public interface UsuarioRepository extends CrudRepository<Usuario, String>, UsuarioRepositoryCustom {
	public Optional<Usuario> findByEmail(@Param("email") String email);
	public Optional<Usuario> findByEmpresaCnpj(@Param("cnpj") String cnpj);
	@Query("SELECT DISTINCT u FROM Usuario u "
			+ "LEFT JOIN FETCH u.pessoa p "
			+ "LEFT JOIN FETCH p.estudante e "
			+ "LEFT JOIN FETCH u.empresa WHERE u.codUsuario = :codUsuario")
	public Optional<Usuario> findByCodUsuario(@Param("codUsuario") Long codUsuario);
	
	@Query("SELECT DISTINCT u FROM Usuario u "
			+ "JOIN FETCH u.pessoa p "
			+ "JOIN FETCH p.estudante e "
			+ "WHERE e.codEstudante = :codEstudante")
	public Optional<Usuario> findByPessoaEstudanteCodEstudante(@Param("codEstudante") Long codEstudante);
	@Query("SELECT DISTINCT u FROM Usuario u "
			+ "JOIN FETCH u.empresa e "
			+ "WHERE e.codEmpresa = :codEmpresa")
	public Optional<Usuario> findByEmpresaCodEmpresa(@Param("codEmpresa") Long codEmpresa);
}