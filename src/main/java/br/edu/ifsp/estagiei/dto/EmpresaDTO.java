package br.edu.ifsp.estagiei.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private List<VagaDTO> vagas;
	private Boolean indAtivo;
	
	public boolean hasVagas() {
		return vagas != null && !vagas.isEmpty();
	}
	
	public boolean hasEndereco() {
		return endereco != null;
	}
}
