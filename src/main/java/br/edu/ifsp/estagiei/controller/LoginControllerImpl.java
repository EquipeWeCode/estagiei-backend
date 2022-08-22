package br.edu.ifsp.estagiei.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.dto.LoginRequestDTO;
import br.edu.ifsp.estagiei.dto.LoginResponseDTO;
import br.edu.ifsp.estagiei.service.LoginService;

@RestController
public class LoginControllerImpl implements LoginController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private LoginService loginService;

	@PostMapping(path = "/login")
	@ResponseBody
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {

		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha()));

			LoginResponseDTO response = loginService.montaAutenticacao(authentication);
			
			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
