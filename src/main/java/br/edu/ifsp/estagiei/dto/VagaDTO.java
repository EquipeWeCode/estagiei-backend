package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class VagaDTO implements DTOUtils {
	private Long codVaga;
	@NotBlank(message = MSG_NOT_NULL)
	private String titulo;
	private String descricao;
	@NotNull(message = MSG_NOT_NULL)
	private BigDecimal salario;
	private EmpresaDTO empresa;	
	private List<CompetenciaDTO> competencias;
	private Boolean indAtivo;
	
	private AuditoriaDTO auditoria;
}
