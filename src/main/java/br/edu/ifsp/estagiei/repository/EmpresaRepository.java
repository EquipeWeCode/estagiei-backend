package br.edu.ifsp.estagiei.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
	
	@Query("SELECT DISTINCT emp FROM Empresa emp JOIN FETCH emp.endereco endereco")
	public List<Empresa> findAll();
}