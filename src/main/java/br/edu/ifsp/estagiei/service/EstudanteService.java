package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.constants.RolesEnum;
import br.edu.ifsp.estagiei.constants.TipoUsuarioEnum;
import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.ContatoDTO;
import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.ExperienciaProfissionalDTO;
import br.edu.ifsp.estagiei.dto.HistoricoEscolarDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.EnderecoDTOFactory;
import br.edu.ifsp.estagiei.dto.factory.EstudanteDTOFactory;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Contato;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.ExperienciaProfissional;
import br.edu.ifsp.estagiei.entity.HistoricoEscolar;
import br.edu.ifsp.estagiei.entity.Permissao;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.facade.IAuthenticationFacade;
import br.edu.ifsp.estagiei.repository.CompetenciaRepository;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.PessoaRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;
import br.edu.ifsp.estagiei.utils.EstagieiUtils;

@Service
@Transactional
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepositorio;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private VagaRepository vagaRepositorio;
	@Autowired
	private PessoaRepository pessoaRepositorio;
	@Autowired
	private CompetenciaRepository competenciaRepositorio;
	@Autowired
	private VagaDTOFactory vagaFactory;
	@Autowired
	private EstudanteDTOFactory estudanteFactory;
	@Autowired
	private EnderecoDTOFactory enderecoFactory;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private IAuthenticationFacade authentication;

	public EstudanteDTO findEstudanteByCodEstudante(Long id) {
		try {
			Estudante estd = estudanteRepositorio.findByCodEstudante(id);
			return estudanteFactory.buildDTO(estd);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public List<VagaDTO> buscaVagasRecomendadas(Long codEstudante, Pageable paginacao) {

		validaEstudanteVaga(codEstudante);

		Page<Vaga> vagas = vagaRepositorio.buscaVagasRecomendadas(codEstudante, paginacao);
		return vagaFactory.buildDTOs(vagas.getContent());
	}

	private void validaEstudanteVaga(Long codEstudante) {
		estudanteRepositorio.findById(codEstudante)
				.orElseThrow(() -> new ValidacaoException("Estudante não encontrado"));
	}

	public EstudanteDTO salvaEstudante(EstudanteDTO dto, boolean isEdicao) {

		Usuario usuario = validaEstudante(dto, isEdicao);

		montaEstudante(usuario, dto, isEdicao);

		Usuario usuarioEstudante = usuarioRepositorio.save(usuario);
		return estudanteFactory.buildDTOUsuario(usuarioEstudante);
	}

	private Usuario validaEstudante(EstudanteDTO dto, boolean isEdicao) {
		String email = dto.getEmail();
		String cpfNumeros = EstagieiUtils.retiraNaoNumericos(dto.getCpf());
		Optional<Usuario> usuarioEmail = usuarioRepositorio.findByEmail(email);
		Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByPessoaEstudanteCodEstudante(dto.getCodEstudante());
		Optional<Pessoa> pessoaCpf = pessoaRepositorio.findByCpf(cpfNumeros);

		if (!isEdicao) {
			if (usuarioEmail.isPresent()) {
				throw new ValidacaoException("Este e-mail já está sendo usado");
			}

		} else {
			if (pessoaCpf.isPresent()) {
				throw new ValidacaoException("CPF já está sendo usado");
			}
			if (!usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Estudante não encontrado");
			}

			validaPermissaoEstudante(dto.getCodEstudante());

		}

		return usuarioBuscado.isPresent() ? usuarioBuscado.get() : new Usuario();
	}

	private void validaPermissaoEstudante(Long codEstudante) {
		Authentication autenticacao = authentication.getAuthentication();
		Usuario usuarioEstudante = (Usuario) autenticacao.getPrincipal();

		Long codUsuario = usuarioEstudante.getCodUsuario();

		Estudante estudanteDoCodUsuario = estudanteRepositorio.findByPessoaUsuarioCodUsuario(codUsuario).orElse(null);

		if (estudanteDoCodUsuario == null) {
			throw new ValidacaoException("Estudante não encontrado");
		}

		if (!estudanteDoCodUsuario.getCodEstudante().equals(codEstudante)) {
			throw new ValidacaoException("Você está tentando alterar um usuário diferente do seu");
		}
	}

	private void montaEstudante(Usuario usuario, EstudanteDTO dto, boolean isEdicao) {
		usuario.setTipoUsuario(TipoUsuarioEnum.ESTUDANTE);

		if (!isEdicao) {
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
			Permissao permissao = new Permissao();
			permissao.setCodPermissao(RolesEnum.ESTUDANTE.getCodigo());
			usuario.addPermissao(permissao);
		}

		Pessoa pessoa = Optional.ofNullable(usuario.getPessoa()).orElse(new Pessoa());
		Estudante estudanteBuscado = Optional.ofNullable(pessoa.getEstudante()).orElse(new Estudante());
		estudanteBuscado.setNvlEscolaridade(dto.getNvlEscolaridade());
		
		salvaPessoa(pessoa, dto, estudanteBuscado, isEdicao);

		List<CompetenciaDTO> competencias = Optional.ofNullable(dto.getCompetencias()).orElse(Lists.newArrayList());
		List<ContatoDTO> contatos = Optional.ofNullable(dto.getContatos()).orElse(Lists.newArrayList());
		List<HistoricoEscolarDTO> historicoEscolar = Optional.ofNullable(dto.getHistoricoEscolar())
				.orElse(Lists.newArrayList());
		List<ExperienciaProfissionalDTO> expProfissional = Optional.ofNullable(dto.getExperienciaProfissional())
				.orElse(Lists.newArrayList());

		usuario.setPessoa(pessoa);
		pessoa.setUsuario(usuario);
		estudanteBuscado.setPessoa(pessoa);

		salvaCompetencias(estudanteBuscado, competencias);
		salvaContatos(pessoa, contatos);
		salvaHistoricoEscolar(estudanteBuscado, historicoEscolar);
		salvaExperienciaProfissional(estudanteBuscado, expProfissional);
	}

	private void salvaHistoricoEscolar(Estudante estudanteBuscado, List<HistoricoEscolarDTO> historicoEscolar) {
		List<HistoricoEscolar> novosHistoricos = Lists.newArrayList();

		for (HistoricoEscolarDTO dto : historicoEscolar) {
			HistoricoEscolar novoHistorico = estudanteBuscado.novoHistoricoEscolar(dto.getCodHistEscolar());
			novoHistorico.setCurso(dto.getCurso());
			novoHistorico.setDataFim(dto.getDataFim());
			novoHistorico.setDataInicio(dto.getDataInicio());
			novoHistorico.setInstEnsino(dto.getInstEnsino());
			novoHistorico.setNvlEscolaridade(dto.getNvlEscolaridade());
			novoHistorico.setStatus(dto.getStatus());
			novoHistorico.setEstudante(estudanteBuscado);
			novosHistoricos.add(novoHistorico);
		}
		estudanteBuscado.retemHistoricos(novosHistoricos);
	}

	private void salvaExperienciaProfissional(Estudante estudanteBuscado,
			List<ExperienciaProfissionalDTO> expProfissional) {
		List<ExperienciaProfissional> novasExpProfissional = Lists.newArrayList();

		for (ExperienciaProfissionalDTO dto : expProfissional) {
			ExperienciaProfissional novaExp = estudanteBuscado.novaExperienciaProfissional(dto.getCodExpProfissional());
			novaExp.setCargo(dto.getCargo());
			novaExp.setDataFim(dto.getDataFim());
			novaExp.setDataInicio(dto.getDataInicio());
			novaExp.setDescricao(dto.getDescricao());
			novaExp.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
			novaExp.setNomeEmpresa(dto.getNomeEmpresa());
			novaExp.setEstudante(estudanteBuscado);
			novasExpProfissional.add(novaExp);
		}
		estudanteBuscado.retemExperiencias(novasExpProfissional);
	}

	private void salvaPessoa(Pessoa pessoa, EstudanteDTO dto, Estudante estudanteBuscado, Boolean isEdicao) {
		String cpfNumeros = EstagieiUtils.retiraNaoNumericos(dto.getCpf());
		String rgNumeros = EstagieiUtils.retiraNaoNumericos(dto.getRg());

		pessoa.setDataNascimento(dto.getDataNascimento());

		if (dto.hasNome()) {
			pessoa.setNome(dto.getNome().toUpperCase());
		}

		if (!isEdicao) {
			pessoa.setCpf(cpfNumeros);
			pessoa.setRg(rgNumeros);
		}

		pessoa.setEstudante(estudanteBuscado);
		pessoa.setEndereco(enderecoFactory.buildEntity(dto.getEndereco()));
	}

	private void salvaCompetencias(Estudante estudanteBuscado, List<CompetenciaDTO> competencias) {

		List<Competencia> novasCompetencias = Lists.newArrayList();

		for (CompetenciaDTO dto : competencias) {
			Competencia competenciaValidada = validaCompetencia(dto);
			Competencia novaCompetencia = estudanteBuscado.novaCompetencia(competenciaValidada);
			novasCompetencias.add(novaCompetencia);
		}
		estudanteBuscado.retemCompetencias(novasCompetencias);
	}

	private Competencia validaCompetencia(CompetenciaDTO dto) {
		Competencia competenciaBuscada = competenciaRepositorio.findById(dto.getCodCompetencia()).orElse(null);
		if (competenciaBuscada == null) {
			throw new ValidacaoException(String.format("A competência %d não existe", dto.getCodCompetencia()));
		}

		return competenciaBuscada;
	}

	private void salvaContatos(Pessoa pessoa, List<ContatoDTO> contatos) {
		List<Contato> novosContatos = Lists.newArrayList();

		for (ContatoDTO dto : contatos) {
			Contato novoContato = pessoa.novoContato(dto.getCodContato());
			novoContato.setDescContato(dto.getDescContato());
			novoContato.setTipoContato(dto.getTipoContato());
			novoContato.setValorContato(dto.getValorContato());
			novosContatos.add(novoContato);
		}
		pessoa.retemContatos(novosContatos);
	}

	public List<EstudanteDTO> buscaTodos(EstudanteFiltroDTO filtro) {
		List<Estudante> estudantes = estudanteRepositorio.buscaTodosPorFiltro(filtro);
		return estudanteFactory.buildDTOs(estudantes);
	}

}
