package br.edu.ifsp.estagiei.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.constants.OperacaoAdministradorEnum;
import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface AdministracaoController extends Controller {
	
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Realizar operação administrativa no nível da empresa", tags = { ADMINISTRADOR })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<EmpresaDTO> putOperacaoEmpresa(OperacaoAdministradorEnum operacao, Long codEmpresa);
}
