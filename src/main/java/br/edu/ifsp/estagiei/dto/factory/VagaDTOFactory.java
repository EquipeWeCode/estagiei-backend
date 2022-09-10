package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO.VagaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class VagaDTOFactory {

	@Autowired
	private CompetenciaDTOFactory competenciaFactory;
	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;

	private EmpresaDTOFactory empresaFactory;

	@Autowired
	public VagaDTOFactory(@Lazy EmpresaDTOFactory empresaFactory) {
		this.empresaFactory = empresaFactory;
	}

	public Set<Vaga> buildEntities(Collection<VagaDTO> dto) {
		return dto.stream().map(this::buildEntity).collect(Collectors.toSet());
	}

	public Vaga buildEntity(VagaDTO dto) {
		Vaga vaga = new Vaga();
		vaga.setCodVaga(dto.getCodVaga());
		vaga.setDescricao(dto.getDescricao());
		vaga.setTitulo(dto.getTitulo());
		vaga.setSalario(dto.getSalario());
		vaga.setEmpresa(this.buildEmpresa(dto.getEmpresa()));
		vaga.setCompetencias(competenciaFactory.buildEntities(dto.getCompetencias()));
		vaga.setIndAtivo(dto.getIndAtivo());
		return vaga;
	}

	private Empresa buildEmpresa(EmpresaDTO dto) {
		Empresa empresa = new Empresa();
		empresa.setCodEmpresa(dto.getCodEmpresa());
		return empresa;
	}

	public List<VagaDTO> buildDTOs(Collection<Vaga> vagas) {
		return vagas.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public VagaDTO buildDTO(Vaga vaga) {
		VagaDTOBuilder builder = VagaDTO.builder();

		builder.codVaga(vaga.getCodVaga()).descricao(vaga.getDescricao()).titulo(vaga.getTitulo())
				.indAtivo(vaga.getIndAtivo()).salario(vaga.getSalario())
				.auditoria(auditoriaFactory.buildDTO(vaga.getAuditoria()));

		if (vaga.hasEmpresa()) {
			builder.empresa(empresaFactory.buildDTO(vaga.getEmpresa()));
		}

		if (vaga.hasCompetencias()) {
			builder.competencias(competenciaFactory.buildDTOs(vaga.getCompetencias()));
		}

		return builder.build();
	}
}
