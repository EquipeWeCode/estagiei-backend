package br.edu.ifsp.estagiei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.estagiei.constants.OperacaoAdministradorEnum;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.service.AdministracaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(AdministracaoControllerImpl.PATH)
public class AdministracaoControllerImpl implements AdministracaoController {

	@Autowired
	private AdministracaoService service;

	public static final String PATH = "/administracao";
	public static final String PATH_EMPRESA = "/{operacao}/empresa/{codEmpresa}";

	@Override
	@PutMapping(PATH_EMPRESA)
	@PreAuthorize("hasAnyAuthority('"  + ROLE_ADMIN + "')")
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<EmpresaDTO> putOperacaoEmpresa(
			@PathVariable(P_OPERACAO) OperacaoAdministradorEnum operacao, @PathVariable(P_COD_EMPRESA) Long codEmpresa) {
		
		return ResponseEntity.ok(service.realizarOperacaoEmpresa(operacao, codEmpresa));
	}
}
