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
import br.edu.ifsp.estagiei.dto.filter.CandidaturaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
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
			.newArrayList(CandidaturaEnum.CANCELADO_ESTUDANTE, CandidaturaEnum.CANDIDATADO);

	public List<CandidaturaDTO> findCandidaturasByCodEstudante(CandidaturaFiltroDTO filtro, Pageable paginacao) {
		try {
			Page<Candidatura> candidaturas = candidaturaRepositorio
					.findCandidaturas(filtro, paginacao);
			return candidaturaFactory.buildDTOs(candidaturas.getContent());
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}
	
	public List<CandidaturaDTO> buscaCandidaturas(CandidaturaFiltroDTO filtro, Pageable paginacao) {
		Page<Candidatura> candidaturas = candidaturaRepositorio.findCandidaturas(filtro, paginacao);
		return candidaturaFactory.buildDTOs(candidaturas.getContent());
	}

	public CandidaturaDTO salvaCandidatura(CandidaturaDTO dto, boolean isEdicao) {

		Candidatura candidatura = validaCandidatura(dto, isEdicao);
		validaPermissaoAlterarStatus(dto);

		if (!isEdicao) {
			criaNovaCandidatura(dto, candidatura);
		}

		candidatura.setStatus(dto.getStatus());

		return candidaturaFactory.buildDTO(candidaturaRepositorio.save(candidatura));
	}

	private void criaNovaCandidatura(CandidaturaDTO dto, Candidatura candidatura) {
		Estudante estudante = new Estudante();
		estudante.setCodEstudante(dto.getCodEstudante());
		Vaga vaga = new Vaga();
		vaga.setCodVaga(dto.getCodVaga());
		candidatura.setEstudante(estudante);
		candidatura.setVaga(vaga);
		candidatura.setCodEstudante(dto.getCodEstudante());
		candidatura.setCodVaga(dto.getCodVaga());
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
			throw new ValidacaoException("Estudante informado não existente");
		}

		try {
			Vaga vaga = vagaRepository.buscaVagaPorId(dto.getCodVaga());
			if (!vaga.getIndAtivo()) {
				throw new ValidacaoException("A vaga informada não está ativa");
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Vaga informada não existente");
		}

		Optional<Candidatura> candidatura = Optional
				.ofNullable(candidaturaRepositorio.findByIds(dto.getCodEstudante(), dto.getCodVaga()));

		if (candidatura.isPresent() && !isEdicao) {
			throw new ValidacaoException("Não é possível se cadastrar em um processo de candidatura já existente");
		}

		if (!candidatura.isPresent() && isEdicao) {
			throw new ValidacaoException("Candidatura não encontrada");
		}

		return candidatura.isPresent() ? candidatura.get() : new Candidatura();
	}

	public void excluiCandidatura(Long codEstudante, Long codVaga) {
		CandidaturaDTO dto = CandidaturaDTO.builder().codEstudante(codEstudante).codVaga(codVaga).build();
		Candidatura candidaturaValidada = validaCandidatura(dto, true);
		candidaturaRepositorio.delete(candidaturaValidada);
	}

}
