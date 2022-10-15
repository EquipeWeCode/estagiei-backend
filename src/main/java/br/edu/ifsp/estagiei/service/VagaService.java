package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.constants.ModalidadeEnum;
import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
import br.edu.ifsp.estagiei.repository.CandidaturaRepository;
import br.edu.ifsp.estagiei.repository.CompetenciaRepository;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
@Transactional
public class VagaService {
	@Autowired
	private VagaRepository vagaRepositorio;
	@Autowired
	private EmpresaRepository empresaRepositorio;
	@Autowired
	private CompetenciaRepository competenciaRepositorio;
	@Autowired
	private CandidaturaRepository candidaturaRepositorio;
	@Autowired
	private VagaDTOFactory factory;
	@Autowired
	private IAuthenticationFacade authentication;

	public List<VagaDTO> buscaTodos(VagaFiltroDTO filtro, Pageable paginacao) {
		Page<Vaga> vagas = vagaRepositorio.buscaTodosPorFiltro(filtro, paginacao);
		return factory.buildDTOs(vagas.getContent());
	}

	public VagaDTO buscaVaga(Long codVaga) {
		try {
			Vaga vaga = vagaRepositorio.buscaVagaPorId(codVaga);
			return factory.buildDTO(vaga);
		} catch (NoResultException e) {
			throw new ValidacaoException("Vaga não encontrada");
		}
	}

	public VagaDTO salvaVaga(VagaDTO dto, boolean isEdicao) {
		validaDadosVaga(dto);
		Vaga vaga = validaPermissaoEmpresa(dto.getCodVaga(), isEdicao);
		montaVaga(vaga, dto);
		Vaga vagaNova = vagaRepositorio.save(vaga);

		if (isEdicao && !vagaNova.isActive()) {
			cancelaTodasCandidaturasDaVaga(vagaNova.getCodVaga());
		}

		Vaga vagaCadastrada = vagaRepositorio.buscaVagaPorId(vagaNova.getCodVaga());
		return factory.buildDTO(vagaCadastrada);
	}

	private void validaDadosVaga(VagaDTO dto) {
		List<ModalidadeEnum> modalidadesComEnderecoObrigatorio = Lists.newArrayList(ModalidadeEnum.PRESENCIAL,
				ModalidadeEnum.HIBRIDO);

		if (dto.hasEndereco()) {

			if (ModalidadeEnum.REMOTO.equals(dto.getModalidade())) {
				throw new ValidacaoException("Vagas remotas não devem ter endereço cadastrado");
			}

		} else if (modalidadesComEnderecoObrigatorio.contains(dto.getModalidade())) {
			throw new ValidacaoException("Endereço obrigatório para vagas híbridas/presenciais");
		}
	}

	private void cancelaTodasCandidaturasDaVaga(Long codVaga) {
		List<Candidatura> candidaturasDaVaga = Optional.ofNullable(candidaturaRepositorio.findByCodVaga(codVaga))
				.orElse(Lists.newArrayList());
		candidaturasDaVaga.forEach(c -> c.setStatus(CandidaturaEnum.CANCELADO));
		candidaturaRepositorio.saveAll(candidaturasDaVaga);
	}

	private void montaVaga(Vaga vaga, VagaDTO dto) {
		Vaga vagaMontada = factory.buildEntitySave(vaga, dto);
		List<CompetenciaDTO> competencias = Optional.ofNullable(dto.getCompetencias()).orElse(Lists.newArrayList());
		salvaCompetencias(vagaMontada, competencias);
	}

	private void salvaCompetencias(Vaga vagaMontada, List<CompetenciaDTO> competencias) {
		List<Competencia> novasCompetencias = Lists.newArrayList();

		for (CompetenciaDTO dto : competencias) {
			Competencia competenciaValidada = validaCompetencia(dto);
			Competencia novaCompetencia = vagaMontada.novaCompetencia(competenciaValidada);
			novasCompetencias.add(novaCompetencia);
		}
		vagaMontada.retemCompetencias(novasCompetencias);
	}

	private Competencia validaCompetencia(CompetenciaDTO dto) {
		Competencia competenciaBuscada = competenciaRepositorio.findById(dto.getCodCompetencia()).orElse(null);
		if (competenciaBuscada == null) {
			throw new ValidacaoException(String.format("A competência %d não existe", dto.getCodCompetencia()));
		}

		return competenciaBuscada;
	}

	private Vaga validaPermissaoEmpresa(Long codVaga, boolean isEdicao) {
		Authentication autenticacao = authentication.getAuthentication();
		Usuario usuarioEmpresa = (Usuario) autenticacao.getPrincipal();
		String email = usuarioEmpresa.getEmail();
		Optional<Vaga> vaga = vagaRepositorio.findByCodVagaAndEmpresaUsuarioEmail(codVaga, email);

		if (isEdicao && vaga.isEmpty()) {
			throw new ValidacaoException("Essa vaga não existe ou não pertence a esta empresa");
		}

		if (!isEdicao && vaga.isEmpty()) {
			Empresa empresaAtual = empresaRepositorio.findByUsuarioEmail(email);
			Vaga vagaNova = new Vaga(empresaAtual);
			return vagaNova;
		}

		return vaga.isPresent() ? vaga.get() : new Vaga();
	}

}
