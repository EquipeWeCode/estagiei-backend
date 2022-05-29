package br.edu.ifsp.estagiei.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.PessoaRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;
import br.edu.ifsp.estagiei.utils.ValidacaoException;

@Service
public class EstudanteService {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EstudanteRepository estudanteRepository;

	public Optional<Estudante> findEstudante(String codUsuario) throws ValidacaoException {
		Optional<Usuario> usuario = usuarioRepository.findById(codUsuario);

		if (usuario.isPresent()) {
			try {
				Optional<Pessoa> pessoa = pessoaRepository.findByUsuarioCodUsuario(usuario.get().getCodUsuario());
				return estudanteRepository.findByPessoaCodPessoa(pessoa.get().getCodPessoa());
			} catch (Exception ex) {
				throw new ValidacaoException("Estudante nao existe");
			}

		} else {
			throw new ValidacaoException("Usuario nao existe");
		}
	}
}
