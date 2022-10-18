package br.edu.ifsp.estagiei.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.estagiei.constants.OperacaoAdministradorEnum;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;

@Service
@Transactional
public class AdministracaoService {

	@Autowired
	private EmpresaService empresaService;

	public EmpresaDTO realizarOperacaoEmpresa(OperacaoAdministradorEnum operacao, Long codEmpresa) {
		EmpresaDTO empresa = empresaService.buscaEmpresa(codEmpresa);
		if (OperacaoAdministradorEnum.APROVAR.equals(operacao)) {
			empresa.setIndAtivo(true);
			empresa = empresaService.alteraEmpresa(empresa, codEmpresa);
		}
		
		if (OperacaoAdministradorEnum.INATIVAR.equals(operacao)) {
			empresa.setIndAtivo(false);
			empresa = empresaService.alteraEmpresa(empresa, codEmpresa);
		}
		
		return empresa;
	}

}
