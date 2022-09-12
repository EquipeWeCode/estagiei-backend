package br.edu.ifsp.estagiei.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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
public class EmpresaDTO implements DTOUtils {
	private Long codEmpresa;
	@NotBlank(message = MSG_NOT_NULL)
	private String email;
	@NotBlank(message = MSG_NOT_NULL)
	@Length(min = 8, max = 25, message = MSG_LENGTH_SENHA)
	private String senha;
	private String avatar;
	@NotBlank(message = MSG_NOT_NULL)
	private String razaoSocial;
	@NotBlank(message = MSG_NOT_NULL)
	private String nomeFantasia;
	@NotBlank(message = MSG_NOT_NULL)
	private String cnpj;
	@NotNull(message = MSG_NOT_NULL)
	@Valid
	private EnderecoDTO endereco;
	private Boolean indAtivo;
	
	private AuditoriaDTO auditoria;
	
	public boolean hasEndereco() {
		return endereco != null;
	}
}
