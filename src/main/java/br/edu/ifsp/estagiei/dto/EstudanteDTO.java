package br.edu.ifsp.estagiei.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class EstudanteDTO implements DTOUtils {
	
	private Long codEstudante;
	private Long codUsuario;
	@NotBlank(message = MSG_NOT_NULL)
	private String email;
	@NotBlank(message = MSG_NOT_NULL)
	@Length(min = 8, max = 25, message = MSG_LENGTH_SENHA)
	private String senha;
	@CPF(message = "CPF Inválido")
	private String cpf;
	@NotBlank(message = MSG_NOT_NULL)
	private String rg;
	@NotBlank(message = MSG_NOT_NULL)
	private String nome;
	private String avatar;
	@NotNull
	@Pattern(regexp = DATE_PATTERN, message = MSG_DATE_FORMAT)
	private String dataNascimento;
	private String instEnsino;
	private String nvlEnsino;
	@Valid
	private EnderecoDTO endereco;
	private List<ExperienciaProfissionalDTO> experienciaProfissional;
	private List<CompetenciaDTO> competencias;
//	private List<HistoricoEscolarDTO> historicoEscolar;
//	private List<ContatoDTO> contatos;
	
	private AuditoriaDTO auditoria;

	public boolean hasDataNascimento() {
		return dataNascimento != null && !dataNascimento.isEmpty();
	}
	
	public boolean hasNome() {
		return nome != null && !nome.isEmpty();
	}
	
	public boolean hasCpf() {
		return cpf != null && !cpf.isEmpty();
	}
	
	public boolean hasCompetencias() {
		return getCompetencias() != null && getCompetencias().size()>0;
	}
}
