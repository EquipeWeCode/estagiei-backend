package br.edu.ifsp.estagiei.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	@Query("SELECT pes FROM Pessoa pes LEFT JOIN FETCH pes.usuario usu WHERE pes.email = :email")
	public Pessoa findByUsuarioEmail(String email);
}
