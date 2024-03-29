package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.constants.CandidaturaEnum;
import br.edu.ifsp.estagiei.dto.CandidaturaDTO;
import br.edu.ifsp.estagiei.dto.filter.CandidaturaFiltroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

public interface CandidaturaController extends Controller {

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna todas as candidaturas do estudante", tags = { CANDIDATURA })
	public ResponseEntity<List<CandidaturaDTO>> getCandidaturasEstudante(Long codEstudante, Pageable paginacao,
			Boolean indAtivo, CandidaturaEnum status);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Retorna candidaturas por filtro", tags = { CANDIDATURA })
	ResponseEntity<List<CandidaturaDTO>> getCandidaturasFiltro(CandidaturaFiltroDTO filtro, Pageable paginacao);

	@ApiResponse(responseCode = "201")
	@Operation(summary = "Inicia um novo processo de candidatura de um estudante", tags = { CANDIDATURA })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<CandidaturaDTO> postCandidatura(Long codEstudante, Long codVaga);

	@ApiResponse(responseCode = "200")
	@Operation(summary = "Atualiza o status da candidatura", tags = { CANDIDATURA })
	@SecurityRequirement(name = "Authorization")
	public ResponseEntity<CandidaturaDTO> putCandidatura(CandidaturaDTO dto);

	@ApiResponse(responseCode = "204")
	@Operation(summary = "Exclui uma candidatura", tags = { CANDIDATURA })
	@SecurityRequirement(name = "Authorization")
	public void deleteCandidatura(Long codEstudante, Long codVaga);

}
