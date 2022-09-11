package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.TipoContatoEnum;
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
	private Long codContato;
    private TipoContatoEnum tipoContato;
    private String descContato;
    private String valorContato;
    
    private AuditoriaDTO auditoria;
}