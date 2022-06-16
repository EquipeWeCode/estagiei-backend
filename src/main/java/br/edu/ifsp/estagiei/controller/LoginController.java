package br.edu.ifsp.estagiei.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface LoginController extends Controller {
	@ApiResponse(responseCode =  "201")
	@Operation(summary = "Faz o login/cadastro do estudante pelo Google", tags = {ESTUDANTE})
	public ResponseEntity<LoginGoogleDTO> login(LoginGoogleDTO loginDTO)
			throws GeneralSecurityException, IOException;
}
