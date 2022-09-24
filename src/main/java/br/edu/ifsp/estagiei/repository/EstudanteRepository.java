package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.repository.custom.EstudanteRepositoryCustom;

public interface EstudanteRepository extends CrudRepository<Estudante, Long>, EstudanteRepositoryCustom {

	public Optional<Estudante> findByPessoaUsuarioCodUsuario(Long codUsuario);
}
