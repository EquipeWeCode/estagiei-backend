package br.edu.ifsp.estagiei.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LoginRequestDTO implements DTOUtils {
	@Email
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "email@email.com")
	private String email;
	@NotBlank(message = MSG_NOT_NULL)
	@Schema(example = "senha1234")
	private String senha;
}
