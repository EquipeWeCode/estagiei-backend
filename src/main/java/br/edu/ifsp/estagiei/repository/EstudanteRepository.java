package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Estudante;

public interface EstudanteRepository extends CrudRepository<Estudante, Long> 
{
	public Optional<Estudante> findByPessoaCodPessoa(Long codPessoa);
}
