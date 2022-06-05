package br.edu.ifsp.estagiei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.factory.EstudanteDTOFactory;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepository;

	@Autowired
	private EstudanteDTOFactory factory;

	public EstudanteDTO findEstudanteByCodEstudante(String id) throws ValidacaoException {
		try {
			Estudante estd = estudanteRepository.buscaPorCodEstudante(id);
			return factory.buildEstudante(estd);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public void insereEstudanteViaGoogle(Payload payload, String estudanteId) {
		String email = payload.getEmail();
		String nome = (String) payload.get("name");
		String avatarUrl = (String) payload.get("picture");

		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);

		usuario.setAvatar(avatarUrl);
		usuario.setEmail(email);
		pessoa.setUsuario(usuario);

		Estudante estudante = new Estudante();
		estudante.setCodEstudante(estudanteId);
		estudante.setPessoa(pessoa);

		estudanteRepository.save(estudante);
	}
}
