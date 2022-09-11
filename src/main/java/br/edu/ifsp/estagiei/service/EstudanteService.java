package br.edu.ifsp.estagiei.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.UsuarioRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

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
	private VagaDTOFactory vagaFactory;
	@Autowired
	private EstudanteDTOFactory estudanteFactory;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public EstudanteDTO findEstudanteByCodEstudante(Long id) {
		try {
			Estudante estd = estudanteRepositorio.findByCodEstudante(id);
			return estudanteFactory.buildDTO(estd);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public List<VagaDTO> buscaVagasRecomendadas(Long codEstudante) {

		validaEstudanteVaga(codEstudante);

		List<Vaga> vagas = vagaRepositorio.buscaVagasRecomendadas(codEstudante);
		return vagaFactory.buildDTOs(vagas);
	}

	private void validaEstudanteVaga(Long codEstudante) {
		estudanteRepositorio.findById(codEstudante)
				.orElseThrow(() -> new ValidacaoException("Estudante não encontrado"));
	}

	public EstudanteDTO salvaEstudante(EstudanteDTO dto, boolean isEdicao) {

		Usuario usuario = validaEstudante(dto, isEdicao);

		montaEstudante(usuario, dto, isEdicao);

		Usuario usuarioEstudante = usuarioRepositorio.save(usuario);
//		Pessoa pessoaEstudante = usuarioEstudante.getPessoa();
//		Estudante estudanteSalvo = pessoaEstudante.getEstudante();
		return estudanteFactory.buildDTOUsuario(usuarioEstudante);
	}

	private Usuario validaEstudante(EstudanteDTO dto, boolean isEdicao) {
		String email = dto.getEmail();
		
		Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByEmail(email);

		if (!isEdicao) {
			if (usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Este e-mail já está sendo usado");
			}
			
			// TODO: Validar unique do CPF da pessoa
		} else {
			if (!usuarioBuscado.isPresent()) {
				throw new ValidacaoException("Estudante não encontrado");
			}
			if (usuarioBuscado.isPresent() && !TipoUsuarioEnum.ESTUDANTE.equals(usuarioBuscado.get().getTipoUsuario())) {
				throw new ValidacaoException("Este e-mail já está sendo usado");
			}
		}

		return usuarioBuscado.isPresent() ? usuarioBuscado.get() : new Usuario();
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
		
		salvaPessoa(pessoa, dto, estudanteBuscado);

		List<CompetenciaDTO> competencias = Optional.ofNullable(dto.getCompetencias()).orElse(Lists.newArrayList());
		List<ContatoDTO> contatos = Optional.ofNullable(dto.getContatos()).orElse(Lists.newArrayList());
		List<HistoricoEscolarDTO> historicoEscolar = Optional.ofNullable(dto.getHistoricoEscolar())
				.orElse(Lists.newArrayList());
		List<ExperienciaProfissionalDTO> expProfissional = Optional.ofNullable(dto.getExperienciaProfissional())
				.orElse(Lists.newArrayList());

//		if (dto.hasCompetencias()) {
//			competencias = dto.getCompetencias();
//		}

		salvaCompetencias(estudanteBuscado, competencias);
		salvaContatos(pessoa, contatos);
		salvaHistoricoEscolar(estudanteBuscado, historicoEscolar);
		salvaExperienciaProfissional(estudanteBuscado, expProfissional);
		
		usuario.setPessoa(pessoa);
		pessoa.setUsuario(usuario);
		pessoa.setEstudante(estudanteBuscado);
		estudanteBuscado.setPessoa(pessoa);
	}

	private void salvaHistoricoEscolar(Estudante estudanteBuscado, List<HistoricoEscolarDTO> historicoEscolar) {
		List<HistoricoEscolar> novosHistoricos = Lists.newArrayList();

		for (HistoricoEscolarDTO dto : historicoEscolar) {
			HistoricoEscolar novoHistorico = estudanteBuscado.novoHistoricoEscolar(dto.getCodHistEscolar());
			novosHistoricos.add(novoHistorico);
		}
		estudanteBuscado.retemHistoricos(novosHistoricos);
	}

	private void salvaExperienciaProfissional(Estudante estudanteBuscado,
			List<ExperienciaProfissionalDTO> expProfissional) {
		List<ExperienciaProfissional> novasExpProfissional = Lists.newArrayList();

		for (ExperienciaProfissionalDTO dto : expProfissional) {
			ExperienciaProfissional novaExp = estudanteBuscado.novaExperienciaProfissional(dto.getCodExpProfissional());
			novasExpProfissional.add(novaExp);
		}
		estudanteBuscado.retemExperiencias(novasExpProfissional);
	}

	private void salvaPessoa(Pessoa pessoa, EstudanteDTO dto, Estudante estudanteBuscado) {
//		Pessoa pessoa = estudanteBuscado.getPessoa();

		String cpfNumeros = dto.hasCpf() ? dto.getCpf().replaceAll("\\D+", "") : "";

		pessoa.setCpf(cpfNumeros);

		if (dto.hasDataNascimento()) {
			LocalDate localDate = LocalDate.parse(dto.getDataNascimento());
			pessoa.setDataNascimento(localDate);
		}

		if (dto.hasNome()) {
			pessoa.setNome(dto.getNome().toUpperCase());
		}
		pessoa.setRg(dto.getRg());
		pessoa.setEstudante(estudanteBuscado);
	}

	private void salvaCompetencias(Estudante estudanteBuscado, List<CompetenciaDTO> competencias) {
		List<Competencia> novasCompetencias = Lists.newArrayList();

		for (CompetenciaDTO dto : competencias) {
			Competencia novaCompetencia = estudanteBuscado.novaCompetencia(dto.getCodCompetencia());
			novasCompetencias.add(novaCompetencia);
		}
		estudanteBuscado.retemCompetencias(novasCompetencias);
	}

	private void salvaContatos(Pessoa pessoa, List<ContatoDTO> contatos) {
		List<Contato> novosContatos = Lists.newArrayList();

		for (ContatoDTO dto : contatos) {
			Contato novoContato = pessoa.novoContato(dto.getCodContato());
			novosContatos.add(novoContato);
		}
		pessoa.retemContatos(novosContatos);
	}

	public List<EstudanteDTO> buscaTodos(EstudanteFiltroDTO filtro) {
		List<Estudante> estudantes = estudanteRepositorio.buscaTodosPorFiltro(filtro);
		return estudanteFactory.buildDTOs(estudantes);
	}

}
