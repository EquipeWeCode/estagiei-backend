package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.HistoricoEscolarDTO;
import br.edu.ifsp.estagiei.dto.HistoricoEscolarDTO.HistoricoEscolarDTOBuilder;
import br.edu.ifsp.estagiei.entity.HistoricoEscolar;

@Component
public class HistoricoEscolarDTOFactory {
	private AuditoriaDTOFactory auditoriaFactory;

	@Autowired
	public HistoricoEscolarDTOFactory(@Lazy AuditoriaDTOFactory auditoriaFactory) {
		this.auditoriaFactory = auditoriaFactory;
	}

	public List<HistoricoEscolarDTO> buildDTOs(Collection<HistoricoEscolar> historicos) {
		return historicos.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public HistoricoEscolarDTO buildDTO(HistoricoEscolar entidade) {
		HistoricoEscolarDTOBuilder builder = HistoricoEscolarDTO.builder();

		return builder
				.codHistEscolar(entidade.getCodHistEscolar())
				.curso(entidade.getCurso())
				.dataFim(entidade.getDataFim())
				.dataInicio(entidade.getDataInicio())
				.instEnsino(entidade.getInstEnsino())
				.nvlEscolaridade(entidade.getNvlEscolaridade())
				.status(entidade.getStatus())
				.auditoria(auditoriaFactory.buildDTO(entidade.getAuditoria()))
				.build();
	}

	public Set<HistoricoEscolar> buildEntities(Collection<HistoricoEscolarDTO> dto) {
		return dto.stream().map(this::buildEntity).collect(Collectors.toSet());
	}

	public HistoricoEscolar buildEntity(HistoricoEscolarDTO dto) {
		HistoricoEscolar entidade = new HistoricoEscolar();
		
		return entidade;
	}
}
