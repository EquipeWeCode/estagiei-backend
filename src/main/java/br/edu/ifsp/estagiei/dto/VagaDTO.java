 package br.edu.ifsp.estagiei.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class VagaDTO implements DTOUtils {
	@Schema(hidden = true)
	private Long codVaga;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Desenvolvedor de software")
	private String titulo;
	@Schema(example = "Venha ser um desenvolvedor de software em nossa empresa!!")
	private String descricao;
	@NotNull(message = MSG_NOT_NULL)
	@Schema(example = "1200.00")
	private BigDecimal salario;
	@Schema(example = "Ciência da Computação")
	private String curso;
	@NotNull(message = MSG_NOT_NULL)
	@Max(value = 6)
	@Schema(example = "6")
	private Integer cargaHoraria;
	private ModalidadeEnum modalidade;
	@Schema(hidden = true)
	private EmpresaDTO empresa;	
	private EnderecoDTO endereco;
	private List<CompetenciaDTO> competencias;
	@Schema(hidden = true)
	private Boolean indAtivo;
	@Schema(hidden = true)
	private AuditoriaDTO auditoria;
}
