package br.edu.ifsp.estagiei.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class EmpresaDTO implements DTOUtils {
	@Schema(hidden = true)
	private Long codEmpresa;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "empresaSwagger@empresa.com")
	private String email;
	@NotBlank(message = MSG_NOT_NULL)
	@Length(min = 8, max = 25, message = MSG_LENGTH_SENHA)
	@Schema(example = "senha1234")
	private String senha;
	@Schema(example = "https://dummyimage.com/600x400/000/fff&text=company")
	private String avatar;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Empresa Swagger LTDA")
	private String razaoSocial;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "Empresa Swagger")
	private String nomeFantasia;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "50368351000128")
	private String cnpj;
	@NotNull(message = MSG_NOT_NULL)
	@Valid
	private EnderecoDTO endereco;
	@Schema(hidden = true)
	private Boolean indAtivo;
	@Schema(hidden = true)
	private AuditoriaDTO auditoria;
	
	public boolean hasEndereco() {
		return endereco != null;
	}
}
