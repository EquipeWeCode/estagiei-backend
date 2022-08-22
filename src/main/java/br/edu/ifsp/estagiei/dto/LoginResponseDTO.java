package br.edu.ifsp.estagiei.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class LoginResponseDTO {
	private String accessToken;
	private final String tokenType = "bearer";
	private List<String> roles;
	private Long expiresIn;
	
	public LoginResponseDTO(String accessToken, List<String> roles, Long expiresIn) {
		this.accessToken = accessToken;
		this.roles = roles;
		this.expiresIn = expiresIn;
	}
}
