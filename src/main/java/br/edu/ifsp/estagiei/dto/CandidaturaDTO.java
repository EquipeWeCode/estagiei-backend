package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
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

	@Schema(example = "Mario dos Santos")
	private String nomeEstudante;

	@Schema(example = "Desenvolvedor de software")
	private String titulo;

	@Schema(example = "Ci�ncia da Computa��o")
	private String curso;

	@Schema(example = "CANDIDATADO")
	private CandidaturaEnum status;

}
