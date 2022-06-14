package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class VagaDTO {
	
	private Long codVaga;
	
	private String descricao;
	
	private EmpresaDTO empresa;
	
	private String razaoSocial;
	
	private String nomeFantasia;
	
	private BigDecimal salario;
	
	private String titulo;
	
	private List<CompetenciaDTO> competencias;

	private Boolean indAtivo;
}
