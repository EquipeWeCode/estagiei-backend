package br.edu.ifsp.estagiei.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.edu.ifsp.estagiei.dto.CompetenciaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CompetenciaController extends Controller {
	@ApiResponse(responseCode =  "200")
	@Operation(summary = "Retorna o catálogo de competências", tags = {COMPETENCIA})
	public ResponseEntity<List<CompetenciaDTO>> getCompetencias();
}
