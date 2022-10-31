package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CompetenciaDTOFactory {
	public List<CompetenciaDTO> buildDTOs(Collection<Competencia> competencias) {
		if (EstagieiUtils.isListEmptyOrNull(competencias)) {
			return Lists.newArrayList();
		}
		return competencias.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public CompetenciaDTO buildDTO(Competencia comp) {
		CompetenciaDTO dto = new CompetenciaDTO();
		dto.setCodCompetencia(comp.getCodCompetencia());
		dto.setDescricaoCompetencia(comp.getDescricao());
		return dto;
	}

	public Set<Competencia> buildEntities(Collection<CompetenciaDTO> dtos) {
		if (dtos != null && dtos.size() > 0) {
			return dtos.stream().map(this::buildEntity).collect(Collectors.toSet());
		}
		return new HashSet<>();
	}

	public Competencia buildEntity(CompetenciaDTO dto) {
		Competencia entidade = new Competencia();
		entidade.setCodCompetencia(dto.getCodCompetencia());
		entidade.setDescricao(dto.getDescricaoCompetencia());
		return entidade;
	}
}
