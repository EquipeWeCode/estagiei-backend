package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.constants.RolesEnum;
import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.factory.EmpresaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.EmpresaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Permissao;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;

@Service
@Transactional
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IAuthenticationFacade authentication;

	@Autowired
	private EmpresaDTOFactory factory;
	
	public EmpresaDTO buscaEmpresa(Long codEmpresa) {
		Empresa empresaBuscada = empresaRepositorio.findById(codEmpresa)
				.orElseThrow(() -> new ValidacaoException("Empresa não encontrada"));

		return factory.buildDTO(empresaBuscada);
	}

	public List<EmpresaDTO> buscaEmpresas(EmpresaFiltroDTO filtro, Pageable paginacao) {
		Page<Empresa> empresas = empresaRepositorio.buscaTodosPorFiltro(filtro, paginacao);
		return factory.buildDTOs(empresas.getContent());
	}

	public EmpresaDTO salvaEmpresa(EmpresaDTO dto, boolean isEdicao) {
		Usuario usuario = validaEmpresa(dto, isEdicao);
		montaEmpresa(usuario, dto, isEdicao);
		Usuario usuarioEmpresa = usuarioRepositorio.save(usuario);
		Empresa empresaSalva = usuarioEmpresa.getEmpresa();
		return factory.buildDTO(empresaSalva);
	}

	private Usuario validaEmpresa(EmpresaDTO dto, boolean isEdicao) {
		String email = dto.getEmail();
		String cnpjNumeros = EstagieiUtils.retiraNaoNumericos(dto.getCnpj());
		Long codEmpresa = dto.getCodEmpresa();

		Optional<Usuario> usuarioEmail = usuarioRepositorio.findByEmail(email);
		Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByEmpresaCodEmpresa(codEmpresa);
		Optional<Usuario> usuarioPorCnpj = usuarioRepositorio.findByEmpresaCnpj(cnpjNumeros);

		if (!isEdicao) {
			if (usuarioEmail.isPresent()) {
				throw new ValidacaoException("Este e-mail já está sendo usado");
			}
			if (usuarioPorCnpj.isPresent()) {
				throw new ValidacaoException("Este CNPJ já está sendo usado");
			}
		} else {
			if (!usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Empresa não encontrada");
			}

			validaPermissaoEmpresa(dto.getCodEmpresa());
		}

		return usuarioBuscado.isPresent() ? usuarioBuscado.get() : new Usuario();
	}

	private void validaPermissaoEmpresa(Long codEmpresa) {
		Authentication autenticacao = authentication.getAuthentication();
		Usuario usuarioEmpresa = (Usuario) autenticacao.getPrincipal();

		Long codUsuario = usuarioEmpresa.getCodUsuario();

		Empresa empresaDoCodUsuario = empresaRepositorio.findByUsuarioCodUsuario(codUsuario).orElse(null);

		if (empresaDoCodUsuario == null) {
			throw new ValidacaoException("Usuário não encontrado");
		}

		if (!empresaDoCodUsuario.getCodEmpresa().equals(codEmpresa)) {
			throw new ValidacaoException("Você está tentando alterar um usuário diferente do seu");
		}
	}

	private void montaEmpresa(Usuario usuario, EmpresaDTO dto, boolean isEdicao) {
		usuario.setTipoUsuario(TipoUsuarioEnum.EMPRESA);

		if (!isEdicao) {
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
			Permissao permissao = new Permissao();
			permissao.setCodPermissao(RolesEnum.EMPRESA.getCodigo());
			usuario.addPermissao(permissao);
		}

		usuario.setAvatar(dto.getAvatar());
		Empresa empresa = usuario.hasEmpresa() ? usuario.getEmpresa() : new Empresa();
		Empresa empresaMontada = factory.buildEntity(empresa, dto, isEdicao);
		empresaMontada.setUsuario(usuario);
		usuario.setEmpresa(empresaMontada);
	}

}
