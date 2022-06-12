package br.edu.ifsp.estagiei.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
public class VagaService {
	@Autowired
	private VagaRepository repositorio;

	@Autowired
	private VagaDTOFactory factory;

	public List<VagaDTO> findAll() throws ValidacaoException {
		try {
			Iterable<Vaga> vagas = repositorio.findAll();
			List<Vaga> vagasLista = new ArrayList<>();
			vagas.forEach(v -> vagasLista.add(v));
			
			return factory.buildLista(vagasLista);
		} catch (EmptyResultDataAccessException e) {
			throw new ValidacaoException("Nenhuma vaga encontrada");
		}
	}
}
