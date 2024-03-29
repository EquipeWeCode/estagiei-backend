package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.LoginResponseDTO;
import br.edu.ifsp.estagiei.entity.Permissao;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.jwt.JwtTokenUtil;

@Service
@Transactional
public class LoginService {

	@Autowired
	private JwtTokenUtil jwtUtil;

	public static final long EXPIRE_DURATION = 180 * 60000; // 10.800.000; 180min

	public LoginResponseDTO montaAutenticacao(Authentication authentication) {
		Usuario user = (Usuario) authentication.getPrincipal();
		String accessToken = jwtUtil.generateAccessToken(user);

		Set<Permissao> permissoes = user.getPermissoes();
		List<String> permissoesString = permissoes.stream().map(p -> p.toString()).collect(Collectors.toList());
		LoginResponseDTO dto = new LoginResponseDTO(accessToken, permissoesString,
				System.currentTimeMillis() + EXPIRE_DURATION);

		return dto;
	}

}
