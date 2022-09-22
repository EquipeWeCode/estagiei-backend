package br.edu.ifsp.estagiei.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.UsuarioDTO;
import br.edu.ifsp.estagiei.dto.factory.UsuarioDTOFactory;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private UsuarioDTOFactory usuarioFactory;

	public UsuarioDTO findByCodUsuario(Long id) {
		Usuario usuario = usuarioRepositorio.findByCodUsuario(id)
				.orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));

		return usuarioFactory.buildDTO(usuario);
	}
}
