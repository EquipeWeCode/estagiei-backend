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
	
	public LoginResponseDTO(String accessToken, List<String> roles) {
		this.accessToken = accessToken;
		this.roles = roles;
	}
}
