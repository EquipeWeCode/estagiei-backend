package br.edu.ifsp.estagiei.dto.factory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CompetenciaDTOFactory {
	public List<CompetenciaDTO> buildCompetencias(Set<Competencia> competencias) {
		return competencias.stream().map(this::buildCompetencia).collect(Collectors.toList());
	}

	public CompetenciaDTO buildCompetencia(Competencia comp) {
		CompetenciaDTO dto = new CompetenciaDTO();
		dto.setCodCompetencia(comp.getCodCompetencia());
		dto.setDescricaoCompetencia(comp.getDescricao());
		return dto;
	}
}
