package br.edu.ifsp.estagiei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.EstudanteDTO;
import br.edu.ifsp.estagiei.dto.factory.EstudanteDTOFactory;
import br.edu.ifsp.estagiei.entity.Estudante;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EstudanteRepositoryCustomImpl;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepositoryCustomImpl estudanteRepository;
	
	@Autowired
	private EstudanteDTOFactory factory;

	public EstudanteDTO findEstudanteByCodUsuario(String codUsuario)
			throws ValidacaoException {
		Estudante est = estudanteRepository.buscaPorCodUsuario(codUsuario);
		return factory.buildEstudante(est);
	}
}
