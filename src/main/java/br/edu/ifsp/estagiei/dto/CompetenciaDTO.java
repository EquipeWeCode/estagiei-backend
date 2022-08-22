package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.NotBlank;

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
public class CompetenciaDTO implements DTOUtils {
	
	private Long codCompetencia;
	@NotBlank(message = MSG_NOT_NULL)
	private String descricaoCompetencia;
}
