package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
@Transactional
public class VagaService {
	@Autowired
	private VagaRepository vagaRepositorio;
	@Autowired
	private VagaDTOFactory factory;
	@Autowired
	private IAuthenticationFacade authentication;

	public List<VagaDTO> buscaTodos(VagaFiltroDTO filtro) {
		List<Vaga> vagas = vagaRepositorio.buscaTodosPorFiltro(filtro);
		return factory.buildDTOs(vagas);
	}

	public VagaDTO salvaVaga(@Valid VagaDTO dto, boolean isEdicao) {
		Vaga vaga = validaPermissaoEmpresa(dto.getCodVaga(), isEdicao);

//		Vaga novaVaga = vagaRepositorio.save(factory.buildEntity(dto));

//		Vaga vagaCadastrada = vagaRepositorio.buscaVagaPorId(novaVaga.getCodVaga());
//		return factory.buildDTO(vagaCadastrada);
		return null;
	}

	private Vaga validaPermissaoEmpresa(Long codVaga, boolean isEdicao) {
		Authentication autenticacao = authentication.getAuthentication();
		Usuario usuarioEmpresa = (Usuario) autenticacao.getPrincipal();
		String email = usuarioEmpresa.getEmail();
		Optional<Vaga> vaga = vagaRepositorio.findByCodVagaAndEmpresaUsuarioEmail(codVaga, email);

		if (isEdicao && vaga.isEmpty()) {
			throw new ValidacaoException("Essa vaga não existe ou não pertence a esta empresa");
		}

		return vaga.isPresent() ? vaga.get() : new Vaga();
	}
}
