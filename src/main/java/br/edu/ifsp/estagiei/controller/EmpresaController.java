package br.edu.ifsp.estagiei.controller;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface EmpresaController extends Controller{

	@ApiResponse(responseCode =  "206")
	@Operation(summary = "Cria uma nova empresa", tags = {EMPRESA})
	public ResponseEntity<EmpresaDTO> postEmpresa(EmpresaDTO dto);
}
