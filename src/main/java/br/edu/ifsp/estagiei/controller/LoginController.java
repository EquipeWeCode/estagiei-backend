package br.edu.ifsp.estagiei.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.LoginRequestDTO;
import br.edu.ifsp.estagiei.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface LoginController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Efetua o login", tags = { LOGIN })
	public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginDTO);
}
