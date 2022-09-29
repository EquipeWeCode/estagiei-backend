package br.edu.ifsp.estagiei.dto.factory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO.CandidaturaDTOBuilder;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Vaga;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class CandidaturaDTOFactory {

	public List<CandidaturaDTO> buildDTOs(Collection<Candidatura> candidaturas) {
		return candidaturas.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public CandidaturaDTO buildDTO(Candidatura candidatura) {
		Vaga vaga = candidatura.getVaga();
		Estudante estudante = candidatura.getEstudante();

		CandidaturaDTOBuilder builder = CandidaturaDTO.builder()
				.codEstudante(candidatura.getCodEstudante())
				.codVaga(candidatura.getCodVaga())
				.nomeEstudante(estudante.getPessoa().getNome())
				.titulo(vaga.getTitulo())
				.curso(vaga.getCurso())
				.status(candidatura.getStatus());

		return builder.build();
	}
	
}
