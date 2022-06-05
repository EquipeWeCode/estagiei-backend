package br.edu.ifsp.estagiei.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.HttpHeaders;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.service.LoginService;

@RestController
public class LoginController implements IController {

	@Autowired
	private LoginService service;

	@PostMapping(ROOT_API + "/login")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ResponseEntity<LoginGoogleDTO> validaLogin(@RequestBody LoginGoogleDTO loginDTO)
			throws GeneralSecurityException, IOException, ValidacaoException {

		HttpHeaders responseHeaders = new HttpHeaders();

		service.validaToken(loginDTO);

		responseHeaders.set("Authentication", loginDTO.getToken());
		return ResponseEntity.ok(loginDTO);
	}
}
