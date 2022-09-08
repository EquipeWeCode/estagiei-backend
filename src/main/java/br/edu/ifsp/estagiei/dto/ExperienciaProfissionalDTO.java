package br.edu.ifsp.estagiei.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
	@NotBlank(message = MSG_NOT_NULL)
	private String nomeEmpresa;
	@NotBlank(message = MSG_NOT_NULL)
	private String cargo;
	@NotBlank(message = MSG_NOT_NULL)
	private String descricao;
	@NotNull(message = MSG_NOT_NULL)
	@Pattern(regexp = DATE_PATTERN, message = MSG_DATE_FORMAT)
	private Timestamp dataInicio;
	@NotNull(message = MSG_NOT_NULL)
	@Pattern(regexp = DATE_PATTERN, message = MSG_DATE_FORMAT)
	private Timestamp dataFim;
	private AuditoriaDTO auditoria;
}
