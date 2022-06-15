package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class VagaDTO implements DTOUtils {
	@NotBlank(message = MSG_NOT_NULL)
	private Long codVaga;
	private String titulo;
	private String descricao;
	private BigDecimal salario;
	private EmpresaDTO empresa;	
	private List<CompetenciaDTO> competencias;
	private Boolean indAtivo;
}
