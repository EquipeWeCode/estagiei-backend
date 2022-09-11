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
	private Long codExpProfissional;
	@NotBlank(message = MSG_NOT_NULL)
	private String nomeEmpresa;
	@NotBlank(message = MSG_NOT_NULL)
	private String cargo;
	@NotBlank(message = MSG_NOT_NULL)
	private String descricao;
	@NotNull(message = MSG_NOT_NULL)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicio;
	@NotNull(message = MSG_NOT_NULL)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFim;
	@Valid
	private EnderecoDTO endereco;
	private AuditoriaDTO auditoria;
}
