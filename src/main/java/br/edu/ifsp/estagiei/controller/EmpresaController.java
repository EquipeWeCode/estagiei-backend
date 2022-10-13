package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.EmpresaDTO;
import br.edu.ifsp.estagiei.dto.filter.EmpresaFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface EmpresaController extends Controller {

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todas as empresas", tags = { EMPRESA })
	public ResponseEntity<List<EmpresaDTO>> getEmpresas(EmpresaFiltroDTO filtro, Pageable paginacao);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna uma empresa de acordo com o código", tags = { EMPRESA })
	public ResponseEntity<EmpresaDTO> getEmpresa(Long codEmpresa);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Cadastra uma nova empresa", tags = { EMPRESA })
	public ResponseEntity<EmpresaDTO> postEmpresa(EmpresaDTO dto);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Edita os dados de uma empresa", tags = { EMPRESA })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<EmpresaDTO> putEmpresa(Long codEmpresa, EmpresaDTO dto);
}
