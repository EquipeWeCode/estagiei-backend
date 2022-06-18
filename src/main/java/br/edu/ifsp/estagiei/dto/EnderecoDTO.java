package br.edu.ifsp.estagiei.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EnderecoDTO implements DTOUtils {
	private Long codEndereco;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private Integer cep;
	private String complemento;
	private Boolean indAtivo;
}
