package br.edu.ifsp.estagiei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.factory.EmpresaDTOFactory;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private EmpresaDTOFactory factory;
	
	public EmpresaDTO salvaEmpresa(EmpresaDTO dto) {
		validaEmpresa(dto);
		Empresa novaEmpresa = empresaRepository.save(factory.buildEntity(dto));
		return factory.buildEmpresa(novaEmpresa);
	}
	
	private void validaEmpresa(EmpresaDTO dto) {
		if (!dto.hasCnpj()) {
			throw new ValidacaoException("É necessário informar um cpnj para a nova empresa.");
		}
	}
	
}
