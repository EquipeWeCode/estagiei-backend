package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
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
public class UsuarioDTO {
	private Long codUsuario;
	private String email;
	private String avatar;
	private TipoUsuarioEnum tipoUsuario;
	private Long codEstudante;
	private Long codEmpresa;
	private AuditoriaDTO auditoria;
}
