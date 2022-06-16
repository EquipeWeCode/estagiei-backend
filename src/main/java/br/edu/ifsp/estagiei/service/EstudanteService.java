package br.edu.ifsp.estagiei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.EstudanteDTOFactory;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepositorio;
	@Autowired
	private VagaRepository vagaRepositorio;
	@Autowired
	private VagaDTOFactory vagaFactory;

	@Autowired
	private EstudanteDTOFactory factory;

	public EstudanteDTO findEstudanteByCodEstudante(String id) {
		try {
			Estudante estd = estudanteRepositorio.findByCodEstudante(id);
			return factory.buildEstudante(estd);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public List<VagaDTO> buscaVagasRecomendadas(String codEstudante) {

		validaEstudanteVaga(codEstudante);

		List<Vaga> vagas = vagaRepositorio.buscaVagasRecomendadas(codEstudante);
		return vagaFactory.buildLista(vagas);
	}

	private void validaEstudanteVaga(String codEstudante) {
		estudanteRepositorio.findById(codEstudante)
				.orElseThrow(() -> new ValidacaoException("Estudante não encontrado"));
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

		estudanteRepositorio.save(estudante);
	}
}
