package br.edu.ifsp.estagiei.dto;

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
public class EnderecoDTO implements DTOUtils {
	@Schema(hidden = true)
	private Long codEndereco;
	@Schema(example = "06123412")
	private String cep;
	@NotNull(message = MSG_NOT_NULL)
	@Length(min = 2, max = 2)
	@Schema(example = "SP")
	private String estado;
	@NotNull(message = MSG_NOT_NULL)
	@Schema(example = "Pinheiros")
	private String cidade;
	@NotNull(message = MSG_NOT_NULL)
	@Schema(example = "Vila Izabel")
	private String bairro;
	@NotNull(message = MSG_NOT_NULL)
	@Schema(example = "Rua Pontes de Oliveira")
	private String logradouro;
	@Schema(example = "23")
	private Integer numero;
	@Schema(example = "Condominio")
	private String complemento;
	@Schema(example = "Estátua São José")
	private String pontoReferencia;
	@Schema(hidden = true)
	@Builder.Default
	private Boolean indAtivo = true;
	@Schema(hidden = true)
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
