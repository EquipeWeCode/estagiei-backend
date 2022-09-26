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

import br.edu.ifsp.estagiei.constants.NvlEscolaridadeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(hidden = true)
	private Long codEstudante;
	@Schema(hidden = true)
	private Long codUsuario;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "alunoSwagger@aluno.com")
	private String email;
	private NvlEscolaridadeEnum nvlEscolaridade;
	@NotBlank(message = MSG_NOT_NULL)
	@Length(min = 8, max = 25, message = MSG_LENGTH_SENHA)
	@Schema(example = "senha1234")
	private String senha;
	@CPF(message = "Inválido")
	@Schema(example = "82059549094")
	private String cpf;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "24573456")
	private String rg;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Maria dos Santos")
	private String nome;
	@Schema(example = "https://dummyimage.com/600x400/000/fff&text=student")
	private String avatar;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(example = "2000-02-02")
	private LocalDate dataNascimento;
	@Valid
	private EnderecoDTO endereco;
	@Valid
	private List<ExperienciaProfissionalDTO> experienciaProfissional;
	@Valid
	private List<CompetenciaDTO> competencias;
	@Valid
	private List<HistoricoEscolarDTO> historicoEscolar;
	@Valid
	private List<ContatoDTO> contatos;
	@Schema(hidden = true)
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
