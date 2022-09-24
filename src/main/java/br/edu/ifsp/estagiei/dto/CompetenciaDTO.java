package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.NotNull;

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
public class CompetenciaDTO implements DTOUtils {
	@NotNull(message = MSG_NOT_NULL)
	@Schema(example = "12")
	private Long codCompetencia;
	@Schema(hidden = true, example = "PENSAMENTO CRÍTICO")
	private String descricaoCompetencia;
}
