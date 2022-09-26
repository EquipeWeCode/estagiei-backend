package br.edu.ifsp.estagiei.dto.factory;

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
	@Autowired
	private HistoricoEscolarDTOFactory histEscolarFactory;
	@Autowired
	private ExperienciaProfissionalFactory expProfissionalFactory;

	public List<EstudanteDTO> buildDTOs(Collection<Estudante> estudantes) {
		return estudantes.stream().map(this::buildDTO).collect(Collectors.toList());
	}

	public EstudanteDTO buildDTO(Estudante estudante) {

		Pessoa pessoaEstudante = estudante.getPessoa();
		Usuario usuarioPessoa = pessoaEstudante.getUsuario();

		EstudanteDTOBuilder builder = EstudanteDTO.builder()
				.codEstudante(estudante.getCodEstudante())
				.nvlEscolaridade(estudante.getNvlEscolaridade())
				.avatar(usuarioPessoa.getAvatar())
				.email(usuarioPessoa.getEmail())
				.cpf((pessoaEstudante.getCpf()))
				.rg(pessoaEstudante.getRg())
				.nome(pessoaEstudante.getNome())
				.contatos(contatoFactory.buildDTOs(pessoaEstudante.getContatos()))
				.experienciaProfissional(expProfissionalFactory.buildDTOs(estudante.getExperienciaProfissional())) // TODO
				.historicoEscolar(histEscolarFactory.buildDTOs(estudante.getHistoricoEscolar()))
				.dataNascimento(pessoaEstudante.getDataNascimento())
				.auditoria(auditoriaFactory.buildDTO(estudante.getAuditoria()));
		
		if (estudante.hasCompetencias()) {
			builder.competencias(competenciaFactory.buildDTOs(estudante.getCompetencias()));
		}

		return builder.build();
	}
	
	public List<EstudanteDTO> buildDTOsUsuario(Collection<Usuario> estudantes) {
		return estudantes.stream().map(this::buildDTOUsuario).collect(Collectors.toList());
	}

	public EstudanteDTO buildDTOUsuario(Usuario usuarioEstudante) {

		Pessoa pessoaEstudante = usuarioEstudante.getPessoa();
		Estudante estudante = pessoaEstudante.getEstudante();

		EstudanteDTOBuilder builder = EstudanteDTO.builder()
				.codEstudante(estudante.getCodEstudante())
				.nvlEscolaridade(estudante.getNvlEscolaridade())
				.avatar(usuarioEstudante.getAvatar())
				.email(usuarioEstudante.getEmail())
				.cpf((pessoaEstudante.getCpf()))
				.rg(pessoaEstudante.getRg())
				.nome(pessoaEstudante.getNome())
				.contatos(contatoFactory.buildDTOs(pessoaEstudante.getContatos()))
				.experienciaProfissional(expProfissionalFactory.buildDTOs(estudante.getExperienciaProfissional())) // TODO
				.historicoEscolar(histEscolarFactory.buildDTOs(estudante.getHistoricoEscolar()))
				.dataNascimento(pessoaEstudante.getDataNascimento())
				.auditoria(auditoriaFactory.buildDTO(usuarioEstudante.getAuditoria()));
		
		if (estudante.hasCompetencias()) {
			builder.competencias(competenciaFactory.buildDTOs(estudante.getCompetencias()));
		}

		return builder.build();
	}
}
