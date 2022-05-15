package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Estudante;

public interface EstudanteRepository extends CrudRepository<Estudante, String> 
{
//	@Query("FROM Estudante e JOIN e.pessoa p WHERE p.email = :email") depois
//	public Optional<Estudante> findByEmail(@Param("email") String email);
}
