package br.edu.ifsp.estagiei.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.repository.custom.EmpresaRepositoryCustom;

public interface EmpresaRepository
		extends CrudRepository<Empresa, Long>, PagingAndSortingRepository<Empresa, Long>, EmpresaRepositoryCustom {

	@Query("SELECT emp FROM Empresa emp LEFT JOIN FETCH emp.usuario LEFT JOIN FETCH emp.endereco WHERE emp.codEmpresa = :codEmpresa")
	public Optional<Empresa> findById(@Param("codEmpresa") Long codEmpresa);

	@Query("SELECT DISTINCT emp FROM Empresa emp LEFT JOIN FETCH emp.endereco endereco LEFT JOIN FETCH emp.usuario")
	public List<Empresa> findAll();

	@Query("SELECT emp FROM Empresa emp LEFT JOIN FETCH emp.usuario usu WHERE usu.email = :email")
	public Empresa findByUsuarioEmail(@Param("email") String email);

	public Optional<Empresa> findByUsuarioCodUsuario(Long codUsuario);
}