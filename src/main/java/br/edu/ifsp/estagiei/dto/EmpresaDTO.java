package br.edu.ifsp.estagiei.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmpresaDTO {
	private Long codEmpresa;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private EnderecoDTO endereco;
	private List<VagaDTO> vagas;
	private Boolean indAtivo;
}
