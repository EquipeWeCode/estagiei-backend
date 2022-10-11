package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.factory.CandidaturaDTOFactory;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
import br.edu.ifsp.estagiei.repository.CandidaturaRepository;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
@Transactional
public class CandidaturaService {

	@Autowired
	private CandidaturaRepository candidaturaRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private EstudanteRepository estudanteRepository;
	@Autowired
	private VagaRepository vagaRepository;
	@Autowired
	private CandidaturaDTOFactory candidaturaFactory;
	@Autowired
	private IAuthenticationFacade authentication;

	private static final List<CandidaturaEnum> statusQueEstudantePodeAlterar = Lists
			.newArrayList(CandidaturaEnum.CANCELADO, CandidaturaEnum.CANDIDATADO);

	public List<CandidaturaDTO> findCandidaturasByCodEstudante(Long id, Pageable paginacao) {
		try {
			Page<Candidatura> candidaturas = candidaturaRepositorio.findCandidaturasByCodEstudante(id, paginacao);
			return candidaturaFactory.buildDTOs(candidaturas.getContent());
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public CandidaturaDTO salvaCandidatura(CandidaturaDTO dto, boolean isEdicao) {

		Candidatura candidatura = validaCandidatura(dto, isEdicao);
		validaPermissaoAlterarStatus(dto);

		if (!isEdicao) {
			candidatura.setCodEstudante(dto.getCodEstudante());
			candidatura.setCodVaga(dto.getCodVaga());
		}

		candidatura.setStatus(dto.getStatus());

		return candidaturaFactory.buildDTO(candidaturaRepositorio.save(candidatura));
	}

	private void validaPermissaoAlterarStatus(CandidaturaDTO dto) {
		Authentication autenticacao = authentication.getAuthentication();
		Usuario usuario = (Usuario) autenticacao.getPrincipal();
		Usuario usuarioBuscado = usuarioRepositorio.findByCodUsuario(usuario.getCodUsuario())
				.orElseThrow(() -> new ValidacaoException("Usuário não encontrado"));
		TipoUsuarioEnum tipoUsuario = usuarioBuscado.getTipoUsuario();

		switch (tipoUsuario) {
		case ESTUDANTE:
			if (!statusQueEstudantePodeAlterar.contains(dto.getStatus())) {
				throw new ValidacaoException(
						"Usuário não tem permissão para atualizar para o status: " + dto.getStatus());
			}
			break;
		default:
			break;
		}
	}

	private Candidatura validaCandidatura(CandidaturaDTO dto, boolean isEdicao) {
		try {
			estudanteRepository.findByCodEstudante(dto.getCodEstudante());
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante informado não existente", e);
		}

		try {
			vagaRepository.buscaVagaPorId(dto.getCodVaga());
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Vaga informada não existente", e);
		}

		Optional<Candidatura> candidatura = Optional
				.of(candidaturaRepositorio.findByIds(dto.getCodEstudante(), dto.getCodVaga()));

		if (candidatura.isPresent() && !isEdicao) {
			throw new ValidacaoException("Não é possível se cadastrar em um processo de candidatura já existente");
		}

		return candidatura.isPresent() ? candidatura.get() : new Candidatura();
	}

}
