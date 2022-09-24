package br.edu.ifsp.estagiei.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.NvlEscolaridadeEnum;
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
public class HistoricoEscolarDTO implements DTOUtils {
	@Schema(hidden = true)
	private Long codHistEscolar;
	@Schema(example = "Análise e Desenvolvimento de Sistemas")
	private String curso;
	private NvlEscolaridadeEnum nvlEscolaridade;
	@Schema(example = "Instituto Federal de São Paulo")
	private String instEnsino;
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(example = "2020-01-01")
	private LocalDate dataInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Schema(example = "2023-01-01")
	private LocalDate dataFim;
	@Schema(example = "Completo")
	private String status;
	@Schema(hidden = true)
	private AuditoriaDTO auditoria;
}
