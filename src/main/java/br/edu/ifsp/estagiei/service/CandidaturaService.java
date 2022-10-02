package br.edu.ifsp.estagiei.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.factory.CandidaturaDTOFactory;
import br.edu.ifsp.estagiei.entity.Candidatura;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.CandidaturaRepository;
import br.edu.ifsp.estagiei.repository.EstudanteRepository;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
@Transactional
public class CandidaturaService {

	@Autowired
	private CandidaturaRepository candidaturaRepositorio;
	@Autowired
	private EstudanteRepository estudanteRepository;
	@Autowired
	private VagaRepository vagaRepository;
	@Autowired
	private CandidaturaDTOFactory candidaturaFactory;

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

		if (!isEdicao) {
			candidatura.setCodEstudante(dto.getCodEstudante());
			candidatura.setCodVaga(dto.getCodVaga());
		}

		candidatura.setStatus(dto.getStatus());

		return candidaturaFactory.buildDTO(candidaturaRepositorio.save(candidatura));
	}

	private Candidatura validaCandidatura(CandidaturaDTO dto, boolean isEdicao) {
		List<Long> ids = Lists.newArrayList();
		if (isEdicao) {
			ids.add(dto.getCodEstudante());
			ids.add(dto.getCodVaga());
		}

		Optional<Candidatura> candidatura = Optional
				.of(candidaturaRepositorio.findByIds(dto.getCodEstudante(), dto.getCodVaga()));
		try {
			estudanteRepository.findById(dto.getCodEstudante());
			vagaRepository.findById(dto.getCodVaga());
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Id informado inválido");
		}

		return candidatura.isPresent() ? candidatura.get() : new Candidatura();
	}

}
