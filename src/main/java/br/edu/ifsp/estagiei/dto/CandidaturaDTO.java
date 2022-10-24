package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
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
public class CandidaturaDTO implements DTOUtils {

	@Schema(example = "77666")
	private Long codEstudante;

	@Schema(example = "77667")
	private Long codVaga;	
	
	@Schema(example = "CANDIDATADO")
	@NotNull
	private CandidaturaEnum status;
	
	@Schema(hidden = true, example = "1231.21")
	private BigDecimal salario;
	
	@Schema(hidden = true, example = "REMOTO")
	private ModalidadeEnum modalidade;

	@Schema(example = "Mario dos Santos")
	private String nomeEstudante;

	@Schema(hidden = true, example = "Desenvolvedor de software")
	private String titulo;

	@Schema(hidden = true, example = "Ciência da Computação")
	private String curso;
	
	@Schema(hidden = true)
	private Boolean indAtivo;

	@Schema(hidden = true)
	private EmpresaDTO empresa;

	@Schema(hidden = true)
	private AuditoriaDTO auditoria;

}
