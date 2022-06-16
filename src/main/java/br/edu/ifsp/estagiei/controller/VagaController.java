package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface VagaController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna a lista de todas as vagas cadastradas", tags = { VAGA })
	public ResponseEntity<List<VagaDTO>> getVagas(VagaFiltroDTO filtro);
}