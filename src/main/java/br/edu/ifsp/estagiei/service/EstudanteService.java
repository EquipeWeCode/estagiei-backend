package br.edu.ifsp.estagiei.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.EstudanteDTOFactory;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.EstudanteFiltroDTO;
import br.edu.ifsp.estagiei.entity.Competencia;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.entity.Pessoa;
import br.edu.ifsp.estagiei.entity.Usuario;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository estudanteRepositorio;
	@Autowired
	private VagaRepository vagaRepositorio;
	@Autowired
	private VagaDTOFactory vagaFactory;
	@Autowired
	private EstudanteDTOFactory estudanteFactory;

	public EstudanteDTO findEstudanteByCodEstudante(Long id) {
		try {
			Estudante estd = estudanteRepositorio.findByCodEstudante(id);
			return estudanteFactory.buildEstudante(estd);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Estudante não encontrado");
		}
	}

	public List<VagaDTO> buscaVagasRecomendadas(Long codEstudante) {

		validaEstudanteVaga(codEstudante);

		List<Vaga> vagas = vagaRepositorio.buscaVagasRecomendadas(codEstudante);
		return vagaFactory.buildLista(vagas);
	}

	private void validaEstudanteVaga(Long codEstudante) {
		estudanteRepositorio.findById(codEstudante)
				.orElseThrow(() -> new ValidacaoException("Estudante não encontrado"));
	}

	public void insereEstudanteViaGoogle(Payload payload, Long codEstudante) {
		String email = payload.getEmail();
		String nome = (String) payload.get("name");
		String avatarUrl = (String) payload.get("picture");

		Usuario usuario = new Usuario();
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(!nome.isEmpty() ? nome.toUpperCase() : "");

		usuario.setAvatar(avatarUrl);
		usuario.setEmail(email);
		pessoa.setUsuario(usuario);

		Estudante estudante = new Estudante();
		estudante.setCodEstudante(codEstudante);
		estudante.setPessoa(pessoa);

		estudanteRepositorio.save(estudante);
	}

	public EstudanteDTO salvaEstudante(EstudanteDTO dto) {
		Estudante estudanteBuscado = null;
		try {			
			estudanteBuscado = estudanteRepositorio.findByCodEstudante(dto.getCodEstudante());
		} catch(NoResultException ex) {
			throw new ValidacaoException("Estudante não encontrado");
		}

		atualizaDadosEstudante(estudanteBuscado, dto);
		
		Estudante estudanteSalvo = estudanteRepositorio.save(estudanteBuscado);
		return estudanteFactory.buildEstudante(estudanteSalvo);
	}

	private void atualizaDadosEstudante(Estudante estudanteBuscado, EstudanteDTO dto) {
		salvaPessoa(estudanteBuscado, dto);
		List<CompetenciaDTO> competencias = Lists.newArrayList();
		if(dto.hasCompetencias()) {			
			competencias = dto.getCompetencias();
		}
		salvaCompetencias(estudanteBuscado, competencias);

	}
	
	private void salvaPessoa(Estudante estudanteBuscado, EstudanteDTO dto) {
		Pessoa pessoa = estudanteBuscado.getPessoa();
		
		String cpfNumeros = dto.hasCpf() ? dto.getCpf().replaceAll("\\D+","") : "";
		
		pessoa.setCpf(cpfNumeros);
		
		if(dto.hasDataNascimento()) {			
			LocalDate localDate = LocalDate.parse(dto.getDataNascimento());
			pessoa.setDataNascimento(localDate);
		}
		
		if(dto.hasNome()) {			
			pessoa.setNome(dto.getNome().toUpperCase());
		}
		pessoa.setRg(dto.getRg());
		estudanteBuscado.setPessoa(pessoa);
	}

	private void salvaCompetencias(Estudante estudanteBuscado, List<CompetenciaDTO> competencias) {
		List<Competencia> novasCompetencias = Lists.newArrayList();

		for (CompetenciaDTO dto : competencias) {
			Competencia novaCompetencia = estudanteBuscado.novaCompetencia(dto.getCodCompetencia());
			novasCompetencias.add(novaCompetencia);
		}
		estudanteBuscado.getCompetencias().retainAll(novasCompetencias);
	}

	public List<EstudanteDTO> buscaTodos(EstudanteFiltroDTO filtro) {
		List<Estudante> estudantes = estudanteRepositorio.buscaTodosPorFiltro(filtro);
		return estudanteFactory.buildLista(estudantes);
	}

}
