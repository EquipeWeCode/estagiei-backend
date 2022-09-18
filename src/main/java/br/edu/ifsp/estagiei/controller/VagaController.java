package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.VagaDTO;
import br.edu.ifsp.estagiei.dto.filter.VagaFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface VagaController extends Controller {
	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna a lista de todas as vagas cadastradas por filtro", tags = { VAGA })
	public ResponseEntity<List<VagaDTO>> getVagas(VagaFiltroDTO filtro);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Cria uma nova vaga", tags = { VAGA })
	public ResponseEntity<VagaDTO> postVaga(VagaDTO dto);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Atualiza uma vaga", tags = { VAGA })
	public ResponseEntity<VagaDTO> putvaga(Long codVaga, VagaDTO dto);
}