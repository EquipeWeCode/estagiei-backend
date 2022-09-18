package br.edu.ifsp.estagiei.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.repository.custom.VagaRepositoryCustom;

public interface VagaRepository extends CrudRepository<Vaga, Long>, VagaRepositoryCustom {

	public Optional<Vaga> findByCodVagaAndEmpresaUsuarioEmail(@Param("codVaga") Long codVaga, @Param("email") String email);
}
