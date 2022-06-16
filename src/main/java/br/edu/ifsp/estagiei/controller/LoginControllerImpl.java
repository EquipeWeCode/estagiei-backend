package br.edu.ifsp.estagiei.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.http.HttpHeaders;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import br.edu.ifsp.estagiei.service.LoginService;

@RestController
public class LoginControllerImpl implements LoginController {

	@Autowired
	private LoginService service;

	@PostMapping(path= "/loginEstudante")
	@ResponseBody
	public ResponseEntity<LoginGoogleDTO> login(@RequestBody @Valid LoginGoogleDTO loginDTO)
			throws GeneralSecurityException, IOException {

		String codEstudante = service.validaToken(loginDTO);
		
		loginDTO.setCodEstudante(codEstudante);

		HttpHeaders responseHeaders = new HttpHeaders(); // TODO: Arrumar
		responseHeaders.set("Authentication", loginDTO.getToken());
		return ResponseEntity.ok(loginDTO);
	}

}
