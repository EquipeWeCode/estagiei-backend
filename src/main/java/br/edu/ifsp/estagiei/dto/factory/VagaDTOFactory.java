package br.edu.ifsp.estagiei.dto.factory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.builder.VagaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class VagaDTOFactory {

	@Autowired
	private CompetenciaDTOFactory competenciaFactory;

	public Set<Vaga> buildVagas(List<VagaDTO> dto) {
		return dto.stream().map(this::buildEntity).collect(Collectors.toSet());
	}

	public Vaga buildEntity(VagaDTO dto) {
		Vaga vaga = new Vaga();
		vaga.setCodVaga(dto.getCodVaga());
		vaga.setDescricao(dto.getDescricao());
		vaga.setTitulo(dto.getTitulo());
		vaga.setSalario(dto.getSalario());
		// vaga.setEmpresa(empresaFactory.buildEntity(dto.getEmpresa()));
		// vaga.setCompetencias(this.buildEntities(dto.getCompetencias());
		vaga.setIndAtivo(dto.getIndAtivo());
		return vaga;
	}

	public List<VagaDTO> buildLista(List<Vaga> vagas) {
		return vagas.stream().map(this::buildVaga).collect(Collectors.toList());
	}

	public VagaDTO buildVaga(Vaga vaga) {
		VagaDTOBuilder builder = VagaDTOBuilder.newInstance();

		builder
				// .empresa(vaga.getEmpresa())
				.codVaga(vaga.getCodVaga()).descricao(vaga.getDescricao()).titulo(vaga.getTitulo())
				.indAtivo(vaga.getIndAtivo()).salario(vaga.getSalario());

		if (vaga.hasCompetencias()) {
			Set<Competencia> competencias = vaga.getCompetencias().stream().collect(Collectors.toSet());
			builder.competencias(competenciaFactory.buildCompetencias(competencias));
		}

		return builder.build();
	}
}
