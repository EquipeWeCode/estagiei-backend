package br.edu.ifsp.estagiei.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.HttpHeaders;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import br.edu.ifsp.estagiei.service.LoginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping("api/login")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	// por enquanto to deixando estourar o erro aqui,
//	mas o ideial é criar uma classe pra excecoes e dar um throw nela la no service
	public ResponseEntity<String> validateLogin(@RequestBody LoginGoogleDTO loginDTO)
			throws GeneralSecurityException, IOException {

		HttpHeaders responseHeaders = new HttpHeaders();

		boolean isTokenValid = service.validateToken(loginDTO);

		if (isTokenValid) {
			responseHeaders.set("Authentication", loginDTO.getToken());
			return ResponseEntity.ok("ok");
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
