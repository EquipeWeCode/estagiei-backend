package br.edu.ifsp.estagiei.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.entity.Auditoria;
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
	private Long codHistEscolar;
	private String curso;
	private String nvlEscolaridade;
	private String instEnsino;
	@Pattern(regexp = DATE_PATTERN, message = MSG_DATE_FORMAT)
	private Timestamp dataInicio;
	@Pattern(regexp = DATE_PATTERN, message = MSG_DATE_FORMAT)
	private Timestamp dataFim;
	private String status;
	private Auditoria auditoria;
}
