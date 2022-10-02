package br.edu.ifsp.estagiei.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface UsuarioController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Busca um usuário", tags = { USUARIO })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<UsuarioDTO> getUsuario(Long codUsuario);
}
