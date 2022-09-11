package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.ExperienciaProfissionalDTO;
import br.edu.ifsp.estagiei.dto.ExperienciaProfissionalDTO.ExperienciaProfissionalDTOBuilder;
import br.edu.ifsp.estagiei.entity.ExperienciaProfissional;

@Component
public class ExperienciaProfissionalFactory {
	
	private AuditoriaDTOFactory auditoriaFactory;

	@Autowired
	public ExperienciaProfissionalFactory(@Lazy AuditoriaDTOFactory auditoriaFactory) {
		this.auditoriaFactory = auditoriaFactory;
	}
	
	public List<ExperienciaProfissionalDTO> buildDTOs(Collection<ExperienciaProfissional> exps) {
		return exps.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public ExperienciaProfissionalDTO buildDTO(ExperienciaProfissional entidade) {
		ExperienciaProfissionalDTOBuilder builder = ExperienciaProfissionalDTO.builder();

		return builder
				.codExpProfissional(entidade.getCodExpProfissional())
				.cargo(entidade.getCargo())
				.dataFim(entidade.getDataFim())
				.dataInicio(entidade.getDataInicio())
				.descricao(entidade.getDescricao())
				.nomeEmpresa(entidade.getNomeEmpresa())
				.auditoria(auditoriaFactory.buildDTO(entidade.getAuditoria()))
				.build();
	}
}
