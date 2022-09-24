package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
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
public class UsuarioDTO {
	@Schema(hidden = true)
	private Long codUsuario;
	@Schema(example = "email@email.com")
	private String email;
	@Schema(example = "https://dummyimage.com/600x400/000/fff&text=user")
	private String avatar;
	private TipoUsuarioEnum tipoUsuario;
	@Schema(example = "email@email.com", nullable = true)
	private Long codEstudante;
	@Schema(example = "email@email.com", nullable = true)
	private Long codEmpresa;
	@Schema(hidden = true)
	private AuditoriaDTO auditoria;
}
