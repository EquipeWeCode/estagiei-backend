package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CandidaturaController extends Controller {

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todas as candidaturas do estudante", tags = { CANDIDATURA })
	public ResponseEntity<List<CandidaturaDTO>> getCandidaturasEstudante(Long codEstudante, Pageable paginacao);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Inicia um novo processo de candidatura de um estudante", tags = { CANDIDATURA })
	public ResponseEntity<CandidaturaDTO> postCandidatura(Long codEstudante, Long codVaga);
	
	@ApiResponse(responseCode = "201")
	@Operation(summary = "Atualiza o status da candidatura", tags = { CANDIDATURA })
	public ResponseEntity<CandidaturaDTO> putCandidatura(CandidaturaDTO dto);

}
