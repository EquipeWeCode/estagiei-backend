package br.edu.ifsp.estagiei.dto.factory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.EstudanteDTO.EstudanteDTOBuilder;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EstudanteDTOFactory {

	@Autowired
	CompetenciaDTOFactory competenciaFactory;
	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;

	public List<EstudanteDTO> buildDTOs(List<Estudante> estudantes) {
		return estudantes.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public EstudanteDTO buildDTO(Estudante estudante) {

		LocalDate dataNasc = estudante.getPessoa().getDataNascimento();
		String dataFormatada = EstagieiUtils.dateParaString(dataNasc);

		EstudanteDTOBuilder builder = EstudanteDTO.builder().codEstudante(estudante.getCodEstudante())
				.avatar(estudante.getPessoa().getUsuario().getAvatar())
				.email(estudante.getPessoa().getUsuario().getEmail()).cpf((estudante.getPessoa().getCpf()))
				.rg(estudante.getPessoa().getRg()).nome(estudante.getPessoa().getNome()).dataNascimento(dataFormatada)
				.auditoria(auditoriaFactory.buildDTO(estudante.getAuditoria()));
//				.contato(estudante.getPessoa().getContato());
		if (estudante.hasCompetencias()) {
			Set<Competencia> competencias = estudante.getCompetencias().stream().collect(Collectors.toSet());
			builder.competencias(competenciaFactory.buildDTOs(competencias));
		}

		return builder.build();
	}
}
