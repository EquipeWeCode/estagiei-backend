package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.UsuarioDTO;
import br.edu.ifsp.estagiei.dto.filter.UsuarioFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface UsuarioController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Busca um usuário por codUsuario", tags = { USUARIO })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<UsuarioDTO> getUsuario(Long codUsuario);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Busca usuários por filtro", tags = { USUARIO })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<List<UsuarioDTO>> getUsuarios(UsuarioFiltroDTO filtroDTO);
}
