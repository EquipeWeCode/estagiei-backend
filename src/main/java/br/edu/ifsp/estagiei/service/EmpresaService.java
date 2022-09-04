package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.factory.EmpresaDTOFactory;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.entity.Permissao;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmpresaDTOFactory factory;

	public EmpresaDTO salvaEmpresa(EmpresaDTO dto, boolean isEdicao) {
		Usuario usuario = validaEmpresa(dto, isEdicao);
		montaEmpresa(usuario, dto, isEdicao);
		Usuario usuarioEmpresa = usuarioRepositorio.save(usuario);
		Empresa empresaSalva = usuarioEmpresa.getEmpresa();
		return factory.buildDTO(empresaSalva);
	}

	private Usuario validaEmpresa(EmpresaDTO dto, boolean isEdicao) {
		String email = dto.getEmail();
		String cnpj = dto.getCnpj();

		Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByEmail(email);
		Optional<Usuario> usuarioPorCnpj = usuarioRepositorio.findByEmpresaCnpj(cnpj);

		if (!isEdicao) {
			if (usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Este e-mail já está sendo usado");
			}

			if (usuarioPorCnpj.isPresent()) {
				throw new ValidacaoException("Uma empresa com esse cnpj já existe");
			}
		} else {
			if (!usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Empresa não encontrada");
			}
		}

		return usuarioBuscado.isPresent() ? usuarioBuscado.get() : new Usuario();
	}

	private void montaEmpresa(Usuario usuario, EmpresaDTO dto, boolean isEdicao) {

		if (!isEdicao) {
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
			Permissao permissao = new Permissao();
			permissao.setCodPermissao(777668L);
			usuario.addPermissao(permissao);
		}

		usuario.setAvatar(dto.getAvatar());
		Empresa empresa = usuario.hasEmpresa() ? usuario.getEmpresa() : new Empresa();
		Empresa empresaMontada = factory.buildEntity(empresa, dto);
		empresaMontada.setUsuario(usuario);
		usuario.setEmpresa(empresaMontada);
	}

	public EmpresaDTO buscaEmpresa(Long codEmpresa) {
		Empresa empresaBuscada = empresaRepositorio.findById(codEmpresa)
				.orElseThrow(() -> new ValidacaoException("Empresa não encontrada"));

		return factory.buildDTO(empresaBuscada);
	}

	public List<EmpresaDTO> buscaEmpresas() {
		List<Empresa> todasEmpresas = empresaRepositorio.findAll();
		List<EmpresaDTO> empresasDTO = Lists.newArrayList();

		todasEmpresas.forEach(e -> empresasDTO.add(factory.buildDTO(e)));
		return empresasDTO;
	}

}
