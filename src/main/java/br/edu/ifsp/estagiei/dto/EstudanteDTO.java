package br.edu.ifsp.estagiei.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@CPF(message = "Inválido")
	private String cpf;
	@NotBlank(message = MSG_NOT_NULL)
	private String rg;
	@NotBlank(message = MSG_NOT_NULL)
	private String nome;
	private String avatar;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private String instEnsino;
	private String nvlEnsino;
	@Valid
	private EnderecoDTO endereco;
	@Valid
	private List<ExperienciaProfissionalDTO> experienciaProfissional;
	private List<CompetenciaDTO> competencias;
	@Valid
	private List<HistoricoEscolarDTO> historicoEscolar;
	@Valid
	private List<ContatoDTO> contatos;
	
	private AuditoriaDTO auditoria;
	
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
