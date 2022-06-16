package br.edu.ifsp.estagiei.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmpresaDTO implements DTOUtils {
	@NotNull(message = MSG_NOT_NULL)
	private Long codEmpresa;
	private String razaoSocial;
	private String nomeFantasia;
	@NotBlank(message = MSG_NOT_NULL)
	private String cnpj;
	private EnderecoDTO endereco;
	private List<VagaDTO> vagas;
	private Boolean indAtivo;
	
	public boolean hasCodEmpresa() {
		return codEmpresa != null;
	}
	
	public boolean hasCnpj() {
		return cnpj != null;
	}

	public boolean hasEndereco() {
		return endereco != null;
	}
	
	public boolean hasVagas() {
		return vagas != null && !vagas.isEmpty();
	}
}
