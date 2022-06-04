package br.edu.ifsp.estagiei.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import br.edu.ifsp.estagiei.dto.LoginGoogleDTO;
import br.edu.ifsp.estagiei.exception.ValidacaoException;

@Service
public class LoginService {

	public void validaToken(LoginGoogleDTO loginDTO) throws GeneralSecurityException, IOException, ValidacaoException {

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				.setAudience(Collections.singletonList(System.getenv("CLIENT_ID"))).build();

		GoogleIdToken idToken = verifier.verify(loginDTO.getToken());
		if (idToken != null) {
			Payload payload = idToken.getPayload();

			String userId = payload.getSubject();
			System.out.println("User ID: " + userId);

			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
//			String locale = (String) payload.get("locale");
//			String familyName = (String) payload.get("family_name");
//			String givenName = (String) payload.get("given_name");

			// Use or store profile information
			// ...

			System.out.println(email + " - " + emailVerified + " - " + name);

		} else {
			throw new ValidacaoException("Token inválido");
		}
	}
}
