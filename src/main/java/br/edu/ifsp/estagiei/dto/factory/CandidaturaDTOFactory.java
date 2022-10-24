package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO.CandidaturaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CandidaturaDTOFactory {
	
	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;
	@Autowired
	private EmpresaDTOFactory empresaFactory;

	public List<CandidaturaDTO> buildDTOs(Collection<Candidatura> candidaturas) {
		return candidaturas.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public CandidaturaDTO buildDTO(Candidatura candidatura) {
		Vaga vaga = Optional.ofNullable(candidatura.getVaga()).orElse(new Vaga());
		Empresa empresa = Optional.ofNullable(vaga.getEmpresa()).orElse(new Empresa());
		Estudante estudante = Optional.ofNullable(candidatura.getEstudante()).orElse(new Estudante());

		CandidaturaDTOBuilder builder = CandidaturaDTO.builder()
				.codEstudante(candidatura.getCodEstudante())
				.codVaga(candidatura.getCodVaga())
				.nomeEstudante(estudante.getPessoa().getNome())
				.titulo(vaga.getTitulo())
				.modalidade(vaga.getModalidade())
				.salario(vaga.getSalario())
				.curso(vaga.getCurso())
				.empresa(empresaFactory.buildDTO(empresa))
				.status(candidatura.getStatus())
				.indAtivo(!CandidaturaEnum.CANCELADO.equals(candidatura.getStatus()))
				.auditoria(auditoriaFactory.buildDTO(candidatura.getAuditoria()));

		return builder.build();
	}

}
