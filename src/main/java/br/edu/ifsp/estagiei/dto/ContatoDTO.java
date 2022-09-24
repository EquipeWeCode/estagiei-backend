package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.TipoContatoEnum;
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
public class ContatoDTO {
	@Schema(hidden = true)
	private Long codContato;
    private TipoContatoEnum tipoContato;
    @Schema(example = "Residencial")
    private String descContato;
    @Schema(example = "1123123141")
    private String valorContato;
    @Schema(hidden = true)
    private AuditoriaDTO auditoria;
}
