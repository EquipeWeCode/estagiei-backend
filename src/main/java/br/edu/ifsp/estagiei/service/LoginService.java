package br.edu.ifsp.estagiei.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class LoginService {

	@Autowired
	private EstudanteRepository estudanteRepository;
	@Autowired
	private EstudanteService estudanteService;

	public String validaToken(LoginGoogleDTO loginDTO) throws GeneralSecurityException, IOException, ValidacaoException {

		String clientId = retornaPrimeiroClientId();

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				.setAudience(Collections.singletonList(clientId)).build();

		GoogleIdToken idToken = verifier.verify(loginDTO.getToken());
		if (idToken != null) {
			
			Payload payload = idToken.getPayload();
			String codEstudante = payload.getSubject();

			insereEstudanteSeNaoExiste(payload);
			return codEstudante;

		} else {
			throw new ValidacaoException("Token inválido");
		}
	}

	private void insereEstudanteSeNaoExiste(Payload payload) {
		String estudanteId = payload.getSubject();

		try {
			estudanteRepository.buscaPorCodEstudante(estudanteId);
		} catch (EmptyResultDataAccessException e) {
			estudanteService.insereEstudanteViaGoogle(payload, estudanteId);
		}
	}

	public String retornaPrimeiroClientId() {
		Dotenv dotenv = null;
		dotenv = Dotenv.configure().load();

		return dotenv.get("CLIENT_ID") != null ? dotenv.get("CLIENT_ID") : System.getenv("CLIENT_ID");
	}
}
