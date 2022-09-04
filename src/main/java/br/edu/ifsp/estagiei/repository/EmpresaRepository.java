package br.edu.ifsp.estagiei.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	@Query("SELECT emp FROM Empresa emp LEFT JOIN FETCH emp.usuario WHERE emp.codEmpresa = :codEmpresa")
	public Optional<Empresa> findById(@Param("codEmpresa") Long codEmpresa);
	
	@Query("SELECT DISTINCT emp FROM Empresa emp LEFT JOIN FETCH emp.endereco endereco LEFT JOIN FETCH emp.usuario")
	public List<Empresa> findAll();
	
	public Empresa findByUsuarioEmail(String email);
}