package br.edu.ifsp.estagiei.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class ExperienciaProfissionalDTO implements DTOUtils {
	@Schema(hidden = true)
	private Long codExpProfissional;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Empresa Teste")
	private String nomeEmpresa;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Estagiário de engenharia de software")
	private String cargo;
	@Schema(example = "Desenvolvi diversos projetos pequenos durante o período")
	private String descricao;
	@NotNull(message = MSG_NOT_NULL)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(example = "2020-02-02")
	private LocalDate dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(example = "2021-02-02")
	private LocalDate dataFim;
	@Valid
	private EnderecoDTO endereco;
	@Schema(hidden = true)
	private AuditoriaDTO auditoria;
}
