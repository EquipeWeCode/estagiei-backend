package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
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
	private VagaDTOFactory factory;
	@Autowired
	private IAuthenticationFacade authentication;

	public List<VagaDTO> buscaTodos(VagaFiltroDTO filtro) {
		List<Vaga> vagas = vagaRepositorio.buscaTodosPorFiltro(filtro);
		return factory.buildDTOs(vagas);
	}

	public VagaDTO salvaVaga(VagaDTO dto, boolean isEdicao) {
		Vaga vaga = validaPermissaoEmpresa(dto.getCodVaga(), isEdicao);
		montaVaga(vaga, dto);
		Vaga vagaNova = vagaRepositorio.save(vaga);

		Vaga vagaCadastrada = vagaRepositorio.buscaVagaPorId(vagaNova.getCodVaga());
		return factory.buildDTO(vagaCadastrada);
	}

	private void montaVaga(Vaga vaga, VagaDTO dto) {
		Vaga vagaMontada = factory.buildEntitySave(vaga, dto);
		List<CompetenciaDTO> competencias = Optional.ofNullable(dto.getCompetencias()).orElse(Lists.newArrayList());
		salvaCompetencias(vagaMontada, competencias);
	}

	private void salvaCompetencias(Vaga vagaMontada, List<CompetenciaDTO> competencias) {
		List<Competencia> novasCompetencias = Lists.newArrayList();

		for (CompetenciaDTO dto : competencias) {
			Competencia novaCompetencia = vagaMontada.novaCompetencia(dto.getCodCompetencia());
			novasCompetencias.add(novaCompetencia);
		}
		vagaMontada.retemCompetencias(novasCompetencias);
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
