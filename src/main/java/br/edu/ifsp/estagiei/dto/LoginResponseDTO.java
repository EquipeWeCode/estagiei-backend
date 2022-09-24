package br.edu.ifsp.estagiei.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class LoginResponseDTO {
	@Schema(example = "token")
	private String accessToken;
	private final String tokenType = "bearer";
	@Schema(example = "[ESTUDANTE]")
	private List<String> roles;
	@Schema(example = "128000")
	private Long expiresIn;
	
	public LoginResponseDTO(String accessToken, List<String> roles, Long expiresIn) {
		this.accessToken = accessToken;
		this.roles = roles;
		this.expiresIn = expiresIn;
	}
}
