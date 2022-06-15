package br.edu.ifsp.estagiei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.VagaFiltroDTO;
import br.edu.ifsp.estagiei.dto.factory.VagaDTOFactory;
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
}
