package br.edu.ifsp.estagiei.dto.factory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.EstudanteDTO.EstudanteDTOBuilder;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class EstudanteDTOFactory {

	@Autowired
	CompetenciaDTOFactory competenciaFactory;
	@Autowired
	private AuditoriaDTOFactory auditoriaFactory;
	@Autowired
	private ContatoDTOFactory contatoFactory;

	public List<EstudanteDTO> buildDTOs(Collection<Estudante> estudantes) {
		return estudantes.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public EstudanteDTO buildDTO(Estudante estudante) {

		Pessoa pessoaEstudante = estudante.getPessoa();
		Usuario usuarioPessoa = pessoaEstudante.getUsuario();
		
		LocalDate dataNasc = pessoaEstudante.getDataNascimento();
		String dataFormatada = EstagieiUtils.dateParaString(dataNasc);

		EstudanteDTOBuilder builder = EstudanteDTO.builder()
				.codEstudante(estudante.getCodEstudante())
				.avatar(usuarioPessoa.getAvatar())
				.email(usuarioPessoa.getEmail())
				.cpf((pessoaEstudante.getCpf()))
				.rg(pessoaEstudante.getRg())
				.nome(pessoaEstudante.getNome())
				.contatos(contatoFactory.buildDTOs(pessoaEstudante.getContatos())) // TODO
				.experienciaProfissional(null) // TODO
				.historicoEscolar(null) // TODO
				.dataNascimento(dataFormatada)
				.auditoria(auditoriaFactory.buildDTO(estudante.getAuditoria()));
		
		if (estudante.hasCompetencias()) {
			builder.competencias(competenciaFactory.buildDTOs(estudante.getCompetencias()));
		}

		return builder.build();
	}
}
