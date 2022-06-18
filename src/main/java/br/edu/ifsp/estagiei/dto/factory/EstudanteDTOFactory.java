package br.edu.ifsp.estagiei.dto.factory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.builder.EstudanteDTOBuilder;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EstudanteDTOFactory {

	@Autowired
	CompetenciaDTOFactory competenciaFactory;

	public List<EstudanteDTO> buildLista(List<Estudante> estudantes) {
		return estudantes.stream().map(this::buildEstudante).collect(Collectors.toList());
	}

	public EstudanteDTO buildEstudante(Estudante estudante) {

		LocalDate dataNasc = estudante.getPessoa().getDataNascimento();
		String dataFormatada = EstagieiUtils.dataNascimentoParaString(dataNasc);

		EstudanteDTOBuilder builder = EstudanteDTOBuilder.newInstance()
				.codEstudante(estudante.getCodEstudante())
				.avatar(estudante.getPessoa().getUsuario().getAvatar())
				.email(estudante.getPessoa().getUsuario().getEmail())
				.cpf(formataCpf(estudante.getPessoa().getCpf()))
				.rg(estudante.getPessoa().getRg())
				.nome(estudante.getPessoa().getNome())
				.instEnsino(estudante.getInstEnsino())
				.nvlEnsino(estudante.getNvlEnsino())
				.expProfissional(estudante.getExpProfissional())
				.dataNascimento(dataFormatada)
				.contato(estudante.getPessoa().getValorContato());

		if (estudante.hasCompetencias()) {
			Set<Competencia> competencias = estudante.getCompetencias().stream().collect(Collectors.toSet());
			builder.competencias(competenciaFactory.buildCompetencias(competencias));
		}

		return builder.build();
	}

	private String formataCpf(String cpf) {
		return (cpf.substring(0,3)+"."+cpf.substring(3,6)+"."+cpf.substring(6,9)+"-"+cpf.substring(9,11));
	}
}
