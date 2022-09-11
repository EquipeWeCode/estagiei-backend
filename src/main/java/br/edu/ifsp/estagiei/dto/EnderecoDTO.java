package br.edu.ifsp.estagiei.dto;

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
public class EnderecoDTO implements DTOUtils {
	private Long codEndereco;
	@NotNull(message = MSG_NOT_NULL)
	private Integer cep;
	@NotNull(message = MSG_NOT_NULL)
	private String logradouro;
	private Integer numero;
	@NotNull(message = MSG_NOT_NULL)
	private String bairro;
	@NotNull(message = MSG_NOT_NULL)
	private String cidade;
	@NotNull(message = MSG_NOT_NULL)
	@Length(min = 2, max = 2, message = "Deve ter 2 caracteres")
	private String estado;
	private String complemento;
	private String pontoReferencia;
	private Boolean indAtivo;
	private AuditoriaDTO auditoria;
	
	public Boolean hasCep() {
		return cep != null;
	}
	
	public Boolean hasBairro() {
		return bairro != null;
	}
	
	public Boolean hasCidade() {
		return cidade != null;
	}
	
	public Boolean hasEstado() {
		return estado != null;
	}
}
