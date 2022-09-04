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
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;
import br.edu.ifsp.estagiei.service.LoginService;

@RestController
public class LoginControllerImpl implements LoginController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private LoginService loginService;
	@Autowired
	EmpresaRepository empresaRepositorio;

	@PostMapping(path = "/login")
	@ResponseBody
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {

		String email = loginDTO.getEmail();
		Empresa empresa = empresaRepositorio.findByUsuarioEmail(email);
		if (empresa != null && !empresa.getIndAtivo()) {
			throw new ValidacaoException("A empresa está inativa, aguarde a liberação dos administradores");
		}

		try {
			String senha = loginDTO.getSenha();

			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

			LoginResponseDTO response = loginService.montaAutenticacao(authentication);

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
