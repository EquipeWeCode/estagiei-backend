package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.EnderecoDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO.VagaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Endereco;
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
	private EnderecoDTOFactory enderecoFactory;
	@Autowired
	private CandidaturaDTOFactory candidaturaFactory;

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
		vaga.setCurso(dto.getCurso());
		vaga.setCargaHoraria(dto.getCargaHoraria());
		vaga.setModalidade(dto.getModalidade());
		vaga.setEndereco(this.buildEndereco(dto.getEndereco()));
		vaga.setEmpresa(this.buildEmpresa(dto.getEmpresa()));
		vaga.setCompetencias(competenciaFactory.buildEntities(dto.getCompetencias()));
		vaga.setIndAtivo(dto.getIndAtivo());
		return vaga;
	}
	
	public Vaga buildEntitySave(Vaga entidade, VagaDTO dto) {
		entidade.setCodVaga(dto.getCodVaga());
		entidade.setDescricao(dto.getDescricao());
		entidade.setTitulo(dto.getTitulo());
		entidade.setSalario(dto.getSalario());
		entidade.setCurso(dto.getCurso());
		entidade.setCargaHoraria(dto.getCargaHoraria());
		entidade.setModalidade(dto.getModalidade());
		
		if(entidade.hasEndereco()) {
			entidade.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
		}
		
		entidade.setIndAtivo(dto.getIndAtivo());
		return entidade;
	}
	
	private Endereco buildEndereco(EnderecoDTO dto) {
		Endereco endereco = new Endereco();
		endereco.setCodEndereco(dto.getCodEndereco());
		return endereco;
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

		builder.codVaga(vaga.getCodVaga())
				.descricao(vaga.getDescricao())
				.salario(vaga.getSalario())
				.titulo(vaga.getTitulo())
				.curso(vaga.getCurso())
				.cargaHoraria(vaga.getCargaHoraria())
				.modalidade(vaga.getModalidade())
				.indAtivo(vaga.getIndAtivo())
				.auditoria(auditoriaFactory.buildDTO(vaga.getAuditoria()));

		if (vaga.hasEmpresa()) {
			builder.empresa(empresaFactory.buildDTO(vaga.getEmpresa()));
		}

		if (vaga.hasCompetencias()) {
			builder.competencias(competenciaFactory.buildDTOs(vaga.getCompetencias()));
		}
		
		if (vaga.hasEndereco()) {
			builder.endereco(enderecoFactory.buildDTO(vaga.getEndereco()));
		}
		
		if (vaga.hasCandidaturas()) {
			builder.candidaturas(candidaturaFactory.buildDTOs(vaga.getCandidaturas()));
		}

		return builder.build();
	}
}
