package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface EmpresaController extends Controller {

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todas as empresas", tags = { EMPRESA })
	public ResponseEntity<List<EmpresaDTO>> getEmpresas();

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna uma empresa de acordo com o código", tags = { EMPRESA })
	public ResponseEntity<EmpresaDTO> getEmpresa(String codEmpresa);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Cadastra uma nova empresa", tags = { EMPRESA })
	public ResponseEntity<EmpresaDTO> postEmpresa(EmpresaDTO dto);
}
