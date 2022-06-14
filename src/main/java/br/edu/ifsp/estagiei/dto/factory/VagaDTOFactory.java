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
	
	public Set<Vaga> buildVagas(List<VagaDTO> dto) {
		return dto.stream().map(this::buildEntity).collect(Collectors.toSet());
	}
	
	public Vaga buildEntity(VagaDTO dto) {
		Vaga vaga = new Vaga();
		vaga.setCodVaga(dto.getCodVaga());
		vaga.setDescricao(dto.getDescricao());
		vaga.setTitulo(dto.getTitulo());
		vaga.setSalario(dto.getSalario());
		//vaga.setEmpresa(empresaFactory.buildEntity(dto.getEmpresa()));
		//vaga.setCompetencias(this.buildEntities(dto.getCompetencias());
		vaga.setIndAtivo(dto.getIndAtivo());
		return vaga;
	}
	
	public List<VagaDTO> buildLista(Set<Vaga> vagas) {
		return vagas.stream().map(this::buildVaga).collect(Collectors.toList());
	}
	
	public VagaDTO buildVaga(Vaga vaga) {
		VagaDTOBuilder builder = VagaDTOBuilder.newInstance();
		
				builder
				//.empresa(vaga.getEmpresa())
				.codVaga(vaga.getCodVaga())
				.descricao(vaga.getDescricao())
				.titulo(vaga.getTitulo())
				.indAtivo(vaga.getIndAtivo())
				.salario(vaga.getSalario());
				
				Set<Competencia> competencias = vaga.getCompetencias().stream() //TODO: Precisa fazer o fetch join para conseguir pegar esses dados
					.map(c -> c.getCompetencia()).collect(Collectors.toSet());
				builder.competencias(buildCompetencias(competencias));
			
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
	
	public List<Competencia> buildEntities(List<CompetenciaDTO> dtos) {
		return dtos.stream().map(this::buildEntity).collect(Collectors.toList());
	}
	
	public Competencia buildEntity(CompetenciaDTO dto) {
		Competencia entidade = new Competencia();
		entidade.setCodCompetencia(dto.getCodCompetencia());
		entidade.setDescricao(dto.getDescricaoCompetencia());
		return entidade;
	}
}
