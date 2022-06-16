package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LoginGoogleDTO implements DTOUtils {
	@NotBlank(message = MSG_NOT_NULL)
	private String token;
	private String codEstudante;
}
