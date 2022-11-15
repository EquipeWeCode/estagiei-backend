package br.edu.ifsp.estagiei.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.constants.OperacaoAdministradorEnum;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.factory.EmpresaDTOFactory;
import br.edu.ifsp.estagiei.entity.Empresa;
import br.edu.ifsp.estagiei.exception.ValidacaoException;
import br.edu.ifsp.estagiei.repository.EmpresaRepository;

@Service
@Transactional
public class AdministracaoService {

	@Autowired
	private EmpresaRepository empresaRepositorio;
	@Autowired
	private EmpresaDTOFactory empresaDTOFactory;

	public EmpresaDTO realizarOperacaoEmpresa(OperacaoAdministradorEnum operacao, Long codEmpresa) {
		Empresa empresa = empresaRepositorio.findById(codEmpresa)
				.orElseThrow(() -> new ValidacaoException("Empresa não encontrada"));

		if (OperacaoAdministradorEnum.APROVAR.equals(operacao)) {
			empresa.setIndAtivo(true);
		}

		if (OperacaoAdministradorEnum.INATIVAR.equals(operacao)) {
			empresa.setIndAtivo(false);
		}

		empresaRepositorio.save(empresa);
		EmpresaDTO dto = empresaDTOFactory.buildDTO(empresa);
		return dto;
	}
}
