package br.edu.ifsp.estagiei.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import br.edu.ifsp.estagiei.entity.Vaga;
import br.edu.ifsp.estagiei.repository.VagaRepository;

@Service
public class VagaService {
	@Autowired
	private VagaRepository vagaRepositorio;

	@Autowired
	private VagaDTOFactory factory;

	public List<VagaDTO> buscaTodos(VagaFiltroDTO filtro) {
		List<Vaga> vagas = vagaRepositorio.buscaTodosPorFiltro(filtro);
		return factory.buildLista(vagas);
	}

	public VagaDTO salvaVaga(@Valid VagaDTO dto) {
		Vaga novaVaga = vagaRepositorio.save(factory.buildEntity(dto));

		Vaga vagaCadastrada = vagaRepositorio.buscaVagaPorId(novaVaga.getCodVaga());
		return factory.buildVaga(vagaCadastrada);
	}
}
