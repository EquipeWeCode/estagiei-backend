package br.edu.ifsp.estagiei.dto.factory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.builder.VagaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class VagaDTOFactory {
	
	public List<VagaDTO> buildLista(List<Vaga> vagas) {
		return vagas.stream().map(this::buildVaga).collect(Collectors.toList());
	}
	
	public VagaDTO buildVaga(Vaga vaga) {
		VagaDTOBuilder builder = VagaDTOBuilder.newInstance();
		
				builder
				.codEmpresa(vaga.getEmpresa().getCodEmpresa())
				.codVaga(vaga.getCodVaga())
				.descricao(vaga.getDescricao())
				.titulo(vaga.getTitulo())
				.indAtivo(vaga.getIndAtivo())
				.salario(vaga.getSalario());
				
//				Set<Competencia> competencias = vaga.getCompetencias().stream() TODO: Precisa fazer o fetch join para conseguir pegar esses dados
//					.map(c -> c.getCompetencia()).collect(Collectors.toSet());
//				builder.competencias(buildCompetencias(competencias));
			
		return builder.build();
	}

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
