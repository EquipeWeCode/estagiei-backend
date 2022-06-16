package br.edu.ifsp.estagiei.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EstudanteDTO implements DTOUtils {
	
	private Long codUsuario;
	private String email;
	private String avatar;
	@Size(min = 11, max=14, message = "deve estar entre 11 e 14 caracteres")
	private String cpf;
	private String rg;
	private String nome;
    @Pattern(regexp = "([0-2][0-9]|3[0-1])\\/(0[1-9]|1[0-2])\\/[0-9]{4}")
	private String dataNascimento;
    @NotNull(message = MSG_NOT_NULL)
	private String codEstudante;
	private String instEnsino;
	private String nvlEnsino;
	private String expProfissional;	
	
	private List<CompetenciaDTO> competencias;
}
