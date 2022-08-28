package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.NotBlank;

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
	@NotBlank(message = MSG_NOT_NULL)
	private Integer cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String complemento;
	private String pontoReferencia;
	private Boolean indAtivo;
}
